package ru.sberbank.mobile.common.animal;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import ru.sberbank.mobile.common.animal.db.StubAnimalsDao;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by Тичер on 17.06.2017.
 */
public class AnimalsStorageTest {

    private StubAnimalsDao mAnimalsDao;
    private AnimalsStorage mAnimalsStorage;

    @Before
    public void setUp() {
        mAnimalsDao = new StubAnimalsDao();
        mAnimalsStorage = new AnimalsStorage(mAnimalsDao);
    }

    @Test
    public void testGetAnimals() {
        mAnimalsDao.insertAnimalsForTest(EntitiesGenerator.createRandomAnimalsList(false));
        List<Animal> actual = mAnimalsStorage.getAnimals();
        assertThat(actual, is(mAnimalsDao.animals));
        assertThat(mAnimalsDao.getAnimalsCalled, is(true));
    }
}
