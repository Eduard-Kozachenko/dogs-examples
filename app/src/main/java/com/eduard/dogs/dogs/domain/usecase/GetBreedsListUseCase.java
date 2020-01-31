package com.eduard.dogs.dogs.domain.usecase;

import com.eduard.dogs.dogs.data.model.DogsListEntity;
import com.eduard.dogs.dogs.domain.executor.PostExecutionThread;
import com.eduard.dogs.dogs.domain.executor.ThreadExecutor;
import com.eduard.dogs.dogs.domain.repository.DogsBreedListRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetBreedsListUseCase extends UseCase<DogsListEntity, Void>{

    private DogsBreedListRepository dogsBreedListRepository;

    @Inject
    GetBreedsListUseCase(DogsBreedListRepository dogsBreedListRepository,
                         ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.dogsBreedListRepository = dogsBreedListRepository;
    }


    @Override
    Observable<DogsListEntity> buildUseCaseObservable(Void unused) {
        return dogsBreedListRepository.getDogsBreedList();
    }
}
