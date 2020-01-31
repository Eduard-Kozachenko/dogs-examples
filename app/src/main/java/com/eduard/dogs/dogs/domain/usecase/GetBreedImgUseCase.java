package com.eduard.dogs.dogs.domain.usecase;


import com.eduard.dogs.dogs.domain.model.BreedImgEntity;
import com.eduard.dogs.dogs.domain.executor.PostExecutionThread;
import com.eduard.dogs.dogs.domain.executor.ThreadExecutor;
import com.eduard.dogs.dogs.domain.repository.BreedImageRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetBreedImgUseCase extends UseCase<BreedImgEntity, GetBreedImgUseCase.Params> {

    private BreedImageRepository repository;

    @Inject
    GetBreedImgUseCase(BreedImageRepository repository, ThreadExecutor threadExecutor,
                   PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<BreedImgEntity> buildUseCaseObservable(Params params) {
        return this.repository.getBreedImages(params.breedImage);
    }

    public static final class Params {

        private final String breedImage;

        private Params(String breedImage) {
            this.breedImage = breedImage;
        }

        public static Params forBreedImages(String breedImage) {
            return new Params(breedImage);
        }
    }
}
