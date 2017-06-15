package ru.sberbank.mobile.common.animal.db;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import ru.sberbank.mobile.common.animal.Animal;
import ru.sberbank.mobile.common.animal.EntitiesGenerator;

import static org.hamcrest.CoreMatchers.everyItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.isIn;
import static org.junit.Assert.*;

/**
 * Created by Тичер on 15.06.2017.
 */
@RunWith(AndroidJUnit4.class)
public class SQLiteAnimalsDaoTest {

    private static final String TEST_NAME = "test.db";

    private SQLiteAnimalsDao mSqliteAnimalsDao;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getTargetContext();
        mSqliteAnimalsDao = new SQLiteAnimalsDao(context, TEST_NAME,
                SQLiteAnimalsDao.CURRENT_VERSION);
    }

    @Test
    public void testInsertAnimal() {
        Animal animal = EntitiesGenerator.createRandomAnimal(false);
        long id = mSqliteAnimalsDao.insertAnimal(animal);
        assertThat(true, is(id > 0));
    }

    @Test
    public void testGetAnimals() {
        List<Animal> expected = EntitiesGenerator.createRandomAnimalsList(false);
        for (Animal animal : expected) {
            animal.setId(mSqliteAnimalsDao.insertAnimal(animal));
        }

        List<Animal> actual = mSqliteAnimalsDao.getAnimals();
        assertThat(actual, everyItem(isIn(expected)));
    }

    @Test
    public void testGetAnimalById() {
        Animal expected = EntitiesGenerator.createRandomAnimal(false);
        long id = mSqliteAnimalsDao.insertAnimal(expected);
        Animal actual = mSqliteAnimalsDao.getAnimalById(id);
        assertThat(actual, is(expected));
    }

    @After
    public void tearDown() {
        mSqliteAnimalsDao.close();
        Context context = InstrumentationRegistry.getTargetContext();
        context.deleteDatabase(TEST_NAME);
    }
}
