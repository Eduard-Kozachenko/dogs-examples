package com.eduard.dogs.dogs.domain.usecase;


import com.eduard.dogs.dogs.domain.model.BreedImgEntity;
import com.eduard.dogs.dogs.domain.executor.PostExecutionThread;
import com.eduard.dogs.dogs.domain.executor.ThreadExecutor;
import com.eduard.dogs.dogs.domain.repository.BreedImageRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

public class GetSubBreedImgUseCase extends UseCase<BreedImgEntity, GetSubBreedImgUseCase.Params> {

    private BreedImageRepository repository;

    @Inject
    GetSubBreedImgUseCase(BreedImageRepository repository, ThreadExecutor threadExecutor,
                          PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.repository = repository;
    }

    @Override
    Observable<BreedImgEntity> buildUseCaseObservable(Params params) {
        return this.repository.getBreedSubImages(params.breedImage,params.breedSubImage);
    }

    public static final class Params {

        private final String breedImage;
        private final String breedSubImage;

        private Params(String breedImage , String breedSubImage) {
            this.breedImage = breedImage;
            this.breedSubImage = breedSubImage;
        }

        public static Params forBreedSubImages(String breedImage, String breedSubImage) {
            return new Params(breedImage,breedSubImage);
        }
    }
}
