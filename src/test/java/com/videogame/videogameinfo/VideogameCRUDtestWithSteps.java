package com.videogame.videogameinfo;

import com.videogame.testbase.TestBase;
import com.videogame.utils.TestUtils;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;
import org.junit.runner.RunWith;


@RunWith(SerenityRunner.class)
public class VideogameCRUDtestWithSteps extends TestBase {

    static int id = 1 + TestUtils.getRandomValueInt();
    static String name = "Lego Friends";
    static String releasedate = "2021-07-16T19:24:01.924Z";
    static int reviewscore = 7890;
    static String category = "girls";
    static String rating = "ABCD";
//    static int videogameId;


    @Steps
    VideogameSteps videogameSteps;

    @Title("This will create new video game and verifyid")
    @Test
    public void test01() {

        videogameSteps.createNewVideoGame(id, name, releasedate, reviewscore, category, rating).log().all()
                .statusCode(200)
                .extract().response()
                .body().jsonPath();
//        System.out.println("Videogame id is : " + id);
    }

    @Title("This will get videogame by id")
    @Test
    public void test02() {
        videogameSteps.getVideogameByID(id).statusCode(200);
    }

    @Title("This will update videogame by id")
    @Test
    public void test03() {
        id = id;
        name = name + "_new";
        category = category + "_changed";
        rating = rating + "_new";

        videogameSteps.updateVideogame(id, name, releasedate, reviewscore, category, rating).statusCode(200).log().all();
//        videogameSteps.getVideogameByID(id).body("name", equalTo(name));
    }

    @Title("This test will delete the videogame by id")
    @Test
    public void test04() {
        videogameSteps.deleteVideoGame(id).statusCode(200).log().all();
    }

    @Title("This test will get videogame")
    @Test
    public void test05(){
      videogameSteps.getAllVideoGameFromList().log().all().statusCode(200);
    }
}
