package com.videogame.videogameinfo;

import com.videogame.constants.EndPoints;
import com.videogame.model.VideogamesPojo;
import io.restassured.response.Response;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import static io.restassured.RestAssured.given;

public class VideogameSteps {
    @Step("Creating videogame with id: {0},name: {1}, releaseDate: {2},  reviewscore: {3}, category: {4} and rating: {5}")
    public ValidatableResponse createNewVideoGame(int id, String name, String releasedate, int reviewScore, String category, String rating) {


        VideogamesPojo videoGamePojo = new VideogamesPojo();
        videoGamePojo.setId(id);
        videoGamePojo.setName(name);
        videoGamePojo.setReleaseDate(releasedate);
        videoGamePojo.setReviewScore(reviewScore);
        videoGamePojo.setCategory(category);
        videoGamePojo.setRating(rating);

        return SerenityRest.rest()
                .given().log().all()
                .header("Content-Type", "application/json")
                .body(videoGamePojo).accept("application/json")
                .when()
                .post(EndPoints.CREATE_NEW_VIDEOGAMES)
                .then();
    }

    @Step("Getting the videogame information with videogameId ")
    public ValidatableResponse getVideogameByID(int videogameID) {
        return SerenityRest.rest()
                .given().log().all()
                .pathParam("id", videogameID)
                .header("Content-Type", "application/json")
                .when()
                .get(EndPoints.GET_SINGLE_VIDEOGAMES)
                .then();

    }

    @Step("Updating vidogame info with id : {0},name: {1}, releaseDate: {2}, reviewscore: {3}, category: {4} and rating: {5}")
    public ValidatableResponse updateVideogame(int id, String name, String releasedate, int reviewScore, String category, String rating) {

        VideogamesPojo videogamesPojo = new VideogamesPojo();
        return SerenityRest.rest()
                .given().log().all()
                .header("Content-Type", "application/json")
                .pathParam("id", id).accept("application/json")
                .when()
                .body(videogamesPojo).accept("application/json")
                .put(EndPoints.UPDATE_VIDEOGAMES_BY_ID)
                .then();
    }

    @Step("Deleting the videogame with Id ")
    public ValidatableResponse deleteVideoGame(int videogameid) {
        return SerenityRest.rest()
                .given()//.accept("application/json")
                .pathParam("id", videogameid).accept("application/json")
                .when()
                .delete(EndPoints.DELETE_VIDEOGAMES_BY_ID)
                .then();
    }

    @Step("Getting the all video game from data")
    public ValidatableResponse getAllVideoGameFromList() {
        return SerenityRest.rest()
                .given().accept("application/json")
                .when()
                .get(EndPoints.GET_ALL_VIDEOGAMES)
                .then();

    }

}






