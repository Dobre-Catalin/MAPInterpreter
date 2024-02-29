package org.example.interpretergui.Controller;

import org.example.interpretergui.Model.DataStructures.*;
import org.example.interpretergui.Model.Exceptions.MyException;
import org.example.interpretergui.Model.ProgramState.ProgramState;
import org.example.interpretergui.Model.Statements.StatementInterface;
import org.example.interpretergui.Model.Type.ReferenceType;
import org.example.interpretergui.Model.Type.Type;
import org.example.interpretergui.Model.Value.*;
import  org.example.interpretergui.Repository.MemoryRepository;
import  org.example.interpretergui.Repository.RepositoryInterface;

import java.util.Collection;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Controller implements ControllerInterface {
    private final RepositoryInterface repo;
    private BoolValue displayFlag;

    private StringValue programLog;

    private ExecutorService executor;

    public RepositoryInterface getRepo() {
        return repo;
    }

    private Map<Integer, Value> safeGarbageCollector(List<Integer> symbolTableAddresses, Map<Integer, Value> heap) {
        return heap.entrySet().stream()
                .filter(e -> symbolTableAddresses.contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddressesFromSymbolTable(Collection<Value> symbolTableValue, Collection<Value> heap) {
        return Stream.concat(
                heap.stream()
                        .filter(v -> v instanceof ReferenceValue)
                        .map(v -> {
                            ReferenceValue v1 = (ReferenceValue) v;
                            return v1.getAddress();
                        }),
                symbolTableValue.stream()
                        .filter(v -> v instanceof ReferenceValue)
                        .map(v -> {
                            ReferenceValue v1 = (ReferenceValue) v;
                            return v1.getAddress();
                        })
        ).collect(Collectors.toList());
    }

    private List<Integer> getAddressesFromSymbolTable(Collection<Value> symbolTableValues) {
        return symbolTableValues.stream()
                .filter(v -> v instanceof ReferenceValue)
                .map(v -> {
                    ReferenceValue v1 = (ReferenceValue) v;
                    return v1.getAddress();
                })
                .collect(Collectors.toList());
    }

    public Controller(RepositoryInterface repo, BoolValue displayFlag) {
        this.repo = repo;
        this.displayFlag = displayFlag;
        ListInterface<ProgramState> repoCopy = repo.getRepo();
        this.programLog = new StringValue("");
        for(ProgramState program : repoCopy){
            this.programLog.add(new StringValue(program.toString()));
        }
    }

    @Override
    public void setDisplayFlag(BoolValue val){
        this.displayFlag = val;
    }

    @Override
    public void allSteps() throws MyException {
        runTypeChecker();
        executor = Executors.newFixedThreadPool(2);
        ListInterface<ProgramState> programList = removeCompletedProgram(repo.getRepo());
        repo.setProgramList(programList);
        while (!programList.isEmpty().getValue()) {
            oneStepForAll(programList.getList());
            programList = removeCompletedProgram(repo.getRepo());
        }
        executor.shutdownNow();
        repo.setProgramList(programList);
    }


    public void takeAStepForGUI() throws MyException {
        executor = Executors.newFixedThreadPool(16);
        ListInterface<ProgramState> programList = removeCompletedProgram(repo.getRepo());

        // problematic if left unchecked
//        Scanner inputBestie = new Scanner(System.in);

//        int stepCount = 1;
//        String inputForStep;

        if (!programList.isEmpty().getValue()) {
//            System.out.println("Step " + stepCount + ":");
//            inputForStep = inputBestie.nextLine();
//            stepCount++;
            oneStepForAll(programList.getList());
            //remove the completed programs
            programList = removeCompletedProgram(repo.getProgramList());

        }

        executor.shutdownNow();

        // HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        // setPrgList of repository in order to change the repository
        //
        // update the repository state
        repo.setProgramList(programList);

    }

    public void oneStepForAll(List<ProgramState> lista) throws MyException {
        lista.forEach(program -> {
            try {
                this.repo.logProgramExecution(program);
            } catch (MyException e) {
                e.printStackTrace();
            }
        });
        List<Callable<ProgramState>> callList = lista.stream()
                .map((ProgramState program) -> (Callable<ProgramState>) (program::oneStep))
                .collect(Collectors.toList());
        List<ProgramState> newProgramList = null;
        try {
            newProgramList = executor.invokeAll(callList).stream()
                    .map(future -> {
                        try {
                            return future.get();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        return null;
                    })
                    .filter(program -> program != null)
                    .collect(Collectors.toList());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        lista.addAll(newProgramList);

        lista.forEach(program -> {
            try {
                this.repo.logProgramExecution(program);
            } catch (MyException e) {
                e.printStackTrace();
            }
        });

        ListInterface<ProgramState> listInt = new ADTList<ProgramState>();
        lista.forEach(listInt::add);
        repo.setProgramList(listInt);
    }


    @Override
    public void addProgram(ProgramState program) {
        this.repo.addProgram(program);
    }

    void logProgramExecution(StringValue executed){
        this.programLog.add(executed);
    }

     public ListInterface<ProgramState> removeCompletedProgram(ListInterface<ProgramState> programList){
        ///programList.getList().removeIf(program -> !program.isNotCompleted());
        ////return programList;
         List<ProgramState> list;
         list = programList.getList();
         list.stream().filter(ProgramState::isNotCompleted).collect(Collectors.toList());
         ListInterface<ProgramState> newList = new ADTList<>();
         list.forEach(newList::add);
         return newList;
    }

    public void runTypeChecker() throws MyException{
        for (ProgramState state : repo.getRepo().getList()){
            DictionaryInterface<String, Type> typeEnv = new ADTDictionary<>();
            state.getExecutionStack().peek().typecheck(typeEnv);
        }
    }
    
    public ListInterface<ProgramState> getProgramStates(){
        return this.repo.getRepo();
    }
}
