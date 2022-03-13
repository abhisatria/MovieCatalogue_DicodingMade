//package com.abhiwisesa.moviecatalogue.ui.home
//
//import androidx.recyclerview.widget.RecyclerView
//import androidx.test.core.app.ActivityScenario
//import androidx.test.espresso.Espresso.onView
//import androidx.test.espresso.IdlingRegistry
//import androidx.test.espresso.action.ViewActions
//import androidx.test.espresso.action.ViewActions.click
//import androidx.test.espresso.assertion.ViewAssertions.matches
//import androidx.test.espresso.contrib.RecyclerViewActions
//import androidx.test.espresso.matcher.ViewMatchers.*
//import androidx.test.ext.junit.rules.ActivityScenarioRule
//import com.abhiwisesa.moviecatalogue.R
//import com.abhiwisesa.moviecatalogue.core.utils.DataDummy
//import com.abhiwisesa.moviecatalogue.core.utils.EspressoIdlingResource
//import com.abhiwisesa.moviecatalogue.home.HomeActivity
//import org.junit.After
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//
//class HomeActivityTest{
//    private val dummyMovieList = DataDummy.generateDummyMovies()
//    private val dummyTvShowList = DataDummy.generateDummyTvShow()
//
//
//    @get:Rule
//    var activityRule = ActivityScenarioRule(HomeActivity::class.java)
//
//
//    @Before
//    fun setUp() {
//        ActivityScenario.launch(HomeActivity::class.java)
//        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
//    }
//
//    @After
//    fun tearDown() {
//        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
//    }
//
//    @Test
//    fun loadMovies() {
//        onView(withId(R.id.rv_movie))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.rv_movie))
//            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyMovieList.size))
//    }
//
//    @Test
//    fun loadDetailMovie() {
//        onView(withId(R.id.rv_movie))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
//            click()
//        ))
//        onView(withId(R.id.tv_detail_title))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.tv_detail_genre))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.tv_detail_language))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.tv_detail_score))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.text_description))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.tv_detail_date))
//            .check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun loadTvShow() {
//        onView(withId(R.id.tvShow)).perform(click())
//        onView(withId(R.id.rv_tvShow))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.rv_tvShow))
//            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShowList.size))
//    }
//
//
//    @Test
//    fun loadDetailTvShow() {
//        onView(withId(R.id.tvShow)).perform(click())
//        onView(withId(R.id.rv_tvShow))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.rv_tvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
//            click()
//        ))
//
//        onView(withId(R.id.tv_detail_title))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.tv_detail_genre))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.tv_detail_language))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.tv_detail_score))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.text_description))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.tv_detail_date))
//            .check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun loadFavoriteMovie() {
//        onView(withId(R.id.favorite)).perform(click())
//        onView(withId(R.id.rv_movie_favorite))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.rv_movie_favorite))
//            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShowList.size))
//    }
//
//    @Test
//    fun loadFavoriteShow() {
//        onView(withId(R.id.favorite)).perform(click())
//        onView(withText("TV SHOW")).perform(click())
//        onView(withId(R.id.rv_movie_favorite))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.rv_movie_favorite))
//            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(dummyTvShowList.size))
//    }
//
//    @Test
//    fun addDeleteFavoriteTvShow() {
//        //TV Show Fragment
//        onView(withId(R.id.tvShow)).perform(click())
//        onView(withId(R.id.rv_tvShow))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.rv_tvShow)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
//            click()
//        ))
//
//        //Add to Favorite TV Show
//        onView(withId(R.id.action_bookmark))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.action_bookmark)).perform(click())
//        onView(isRoot()).perform(ViewActions.pressBack())
//
//        //Navigate to Favorite TV Show List
//        onView(withId(R.id.favorite)).perform(click())
//        onView(withText("TV SHOW")).perform(click())
//        onView(withId(R.id.rv_movie_favorite))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.rv_movie_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
//            click()
//        ))
//
//        //Remove from Favorite TV Show
//        onView(withId(R.id.action_bookmark))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.action_bookmark)).perform(click())
//        onView(isRoot()).perform(ViewActions.pressBack())
//    }
//
//    @Test
//    fun addDeleteFavoriteMovie() {
//        //Movies Fragment
//        onView(withId(R.id.rv_movie))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.rv_movie)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
//            click()
//        ))
//
//        //Add to Favorite TV Show
//        onView(withId(R.id.action_bookmark))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.action_bookmark)).perform(click())
//        onView(isRoot()).perform(ViewActions.pressBack())
//
//        //Navigate to Favorite TV Show List
//        onView(withId(R.id.favorite)).perform(click())
//        onView(withId(R.id.rv_movie_favorite))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.rv_movie_favorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0,
//            click()
//        ))
//
//        //Remove from Favorite TV Show
//        onView(withId(R.id.action_bookmark))
//            .check(matches(isDisplayed()))
//        onView(withId(R.id.action_bookmark)).perform(click())
//        onView(isRoot()).perform(ViewActions.pressBack())
//    }
//
//}