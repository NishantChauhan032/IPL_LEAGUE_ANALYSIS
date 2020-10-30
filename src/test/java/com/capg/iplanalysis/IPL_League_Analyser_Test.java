package com.capg.iplanalysis;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class IPL_League_Analyser_Test {
	private static final String BATSMAN_DATA_PATH = "./src/test/resources/BatsmanData.csv";
	private static final String BOWLER_DATA_CSV_PATH = "./src/test/resources/BowlerData.csv";
	IPL_League_Analyser ipl_league_analyser = null;

	@Before
	public void setUp() {
		ipl_league_analyser = new IPL_League_Analyser();
		try {
			ipl_league_analyser.loadBatsmanData(BATSMAN_DATA_PATH);
			ipl_league_analyser.loadBowlerData(BOWLER_DATA_CSV_PATH);
		} catch (IPLAnalyserException e) {
		}
	}

	@Test // 1->5
	public void givenCSVFile_shouldReturn_CricketerWithTopBattingAverage() {
		String sortedBatsmanData = ipl_league_analyser.getBestBattingAveragesCricketers();
		Batsman[] sortedBatsmanArray = new Gson().fromJson(sortedBatsmanData, Batsman[].class);
		System.out.println(sortedBatsmanArray[0].getAverage());
		assertEquals("MS Dhoni", sortedBatsmanArray[0].getName());
	}

	@Test // 2->4
	public void givenCSVFile_shouldReturn_CricketerWithTopStrikingRate() {
		String sortedBatsmanData = ipl_league_analyser.getCricketerWithBestStrikeRates();
		Batsman[] sortedBatsmanArray = new Gson().fromJson(sortedBatsmanData, Batsman[].class);
		System.out.println(sortedBatsmanArray[0].getName());
		assertEquals("Andre Russell", sortedBatsmanArray[0].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_CricketerWithMaximumBoundries() {
		String sortedBatsmanData = ipl_league_analyser.getCricketerWithMaximumBoundaries();
		Batsman[] sortedBatsmanArray = new Gson().fromJson(sortedBatsmanData, Batsman[].class);
		System.out.println(sortedBatsmanArray[0].getName() + sortedBatsmanArray[0].getBoundries());
		assertEquals("Andre Russell", sortedBatsmanArray[0].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_CricketerWithMaximumRunsAndWithBestAverages() {
		String sortedBatsmanData = ipl_league_analyser.getCricketerWithMostRuns();
		Batsman[] sortedBatsmanArray = new Gson().fromJson(sortedBatsmanData, Batsman[].class);
		System.out.println(sortedBatsmanArray[0].getName() + sortedBatsmanArray[0].getAverage());
		assertEquals("David Warner", sortedBatsmanArray[0].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_BowlerWithBestBowlingAverages() {
		String sortedBowlerData = ipl_league_analyser.getCricketerWithBestBowlingAverage();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBowlerData, Bowler[].class);
		System.out.println(sortedBowlerArray[0].getName() + sortedBowlerArray[0].getAverage());
		assertEquals("Anukul Roy", sortedBowlerArray[0].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_BowlerWithBestBowlingStrikeRates() {
		String sortedBowlerData = ipl_league_analyser.getCricketerWithBestBowlingStrikeRates();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBowlerData, Bowler[].class);
		System.out.println(sortedBowlerArray[0].getName() + sortedBowlerArray[0].getStrikeRate());
		assertEquals("Alzarri Joseph", sortedBowlerArray[0].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_BowlerWithBestEconomy() {
		String sortedBowlerData = ipl_league_analyser.getBowlerWithBestEconomyRate();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBowlerData, Bowler[].class);
		System.out.println(sortedBowlerArray[0].getName() + sortedBowlerArray[0].getEconomy());
		assertEquals("Shivam Dube", sortedBowlerArray[0].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_BowlerWithBestBowlingStrikeRatesAndWicketHauls() {
		String sortedBowlerData = ipl_league_analyser.getCricketerWithBestBowlingStrikeRates();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBowlerData, Bowler[].class);
		assertEquals("Alzarri Joseph", sortedBowlerArray[0].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_BestBowlingAveragesWithBestStrikeRate() {
		String sortedBowlerData = ipl_league_analyser.getCricketerWithBestBowlingAverage();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBowlerData, Bowler[].class);
		assertEquals("Anukul Roy", sortedBowlerArray[0].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_MaximumWicketsWithBestBowlingAverages() {
		String sortedBowlerData = ipl_league_analyser.getCricketerWithMaximumWickets();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBowlerData, Bowler[].class);
		System.out.println(sortedBowlerArray[0].getName() + sortedBowlerArray[0].getWicketsTaken());
		assertEquals("Imran Tahir", sortedBowlerArray[0].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_CricketersWithBestBattingAndBowlingAverages() {
		String sortedBowlerData = ipl_league_analyser.getCricketerWithBestBowlingAndBattingAverage();
		AllRounder[] sortedAllRounderArray = new Gson().fromJson(sortedBowlerData, AllRounder[].class);
		System.out.println(sortedAllRounderArray[1].getName() + sortedAllRounderArray[1].getBattingAverage()
				+ sortedAllRounderArray[1].getBowlingAverage());
		assertEquals("Andre Russell", sortedAllRounderArray[0].getName());
		assertEquals("Kagiso Rabada", sortedAllRounderArray[1].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_AllRoundersWithMostRunsAndWickets() {
		String sortedAllRounderData = ipl_league_analyser.getCricketerWithMostRunsAndWickets();
		AllRounder[] sortedAllRounderArray = new Gson().fromJson(sortedAllRounderData, AllRounder[].class);
		System.out.println(sortedAllRounderArray[0].getName() + sortedAllRounderArray[0].getRunsScored());
		assertEquals("Andre Russell", sortedAllRounderArray[0].getName());
		assertEquals("Kagiso Rabada", sortedAllRounderArray[1].getName());
	}
	
	@Test
	public void givenCSVFile_shouldReturn_CricketersWithMaximumHundredsAndBestBattingAverages() {
		String sortedBatsmanData = ipl_league_analyser.getCricketerWithMaximumHundereds();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBatsmanData, Bowler[].class);
		assertEquals("David Warner", sortedBowlerArray[0].getName());
	}
	
	@Test
	public void givenCSVFile_shouldReturn_CricketersWithZeroHundredsAndFiftiesButBestBattingAverages() {
		String sortedBatsmanData = ipl_league_analyser.getCricketerWithBestAverageButLessThan50Runs();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBatsmanData, Bowler[].class);
		assertEquals("Marcus Stoinis", sortedBowlerArray[0].getName());
	}
}
