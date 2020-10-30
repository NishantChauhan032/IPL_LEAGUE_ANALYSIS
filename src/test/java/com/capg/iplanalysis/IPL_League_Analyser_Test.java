package com.capg.iplanalysis;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

public class IPL_League_Analyser_Test {
	private static final String BATSMAN_DATA_PATH = "./src/test/resources/BatsmanData.csv";
	private static final String BOWLER_DATA_CSV_PATH = "./src/test/resources/BowlerData.csv";
	IPL_League_Analyser ipl_LeagueAnalyser = null;

	@Before
	public void setUp() {
		ipl_LeagueAnalyser = new IPL_League_Analyser();
		try {
			ipl_LeagueAnalyser.loadBatsmanData(BATSMAN_DATA_PATH);
			ipl_LeagueAnalyser.loadBowlerData(BOWLER_DATA_CSV_PATH);
		} catch (IPLAnalyserException e) {
		}
	}

	@Test // 1->5
	public void givenCSVFile_shouldReturn_CricketersWith_TopBattingAverages() {
		String sortedBatsmanData = ipl_LeagueAnalyser.getBestBattingAveragesCricketers();
		Batsman[] sortedBatsmanArray = new Gson().fromJson(sortedBatsmanData, Batsman[].class);
		System.out.println(sortedBatsmanArray[0].getAverage());
		assertEquals("MS Dhoni", sortedBatsmanArray[0].getName());
	}

	@Test // 2->4
	public void givenCSVFile_shouldReturn_CricketersWith_TopStrikingRates() {
		String sortedBatsmanData = ipl_LeagueAnalyser.getBestStrikeRateCricketers();
		Batsman[] sortedBatsmanArray = new Gson().fromJson(sortedBatsmanData, Batsman[].class);
		System.out.println(sortedBatsmanArray[0].getName());
		assertEquals("Andre Russell", sortedBatsmanArray[0].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_MaximumBoundries() {
		String sortedBatsmanData = ipl_LeagueAnalyser.getMaximumBoundriesCricketers();
		Batsman[] sortedBatsmanArray = new Gson().fromJson(sortedBatsmanData, Batsman[].class);
		System.out.println(sortedBatsmanArray[0].getName() + sortedBatsmanArray[0].getBoundries());
		assertEquals("Andre Russell", sortedBatsmanArray[0].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_MaximumRuns_WithBestAverages() {
		String sortedBatsmanData = ipl_LeagueAnalyser.getMaximumRunsCricketers();
		Batsman[] sortedBatsmanArray = new Gson().fromJson(sortedBatsmanData, Batsman[].class);
		System.out.println(sortedBatsmanArray[0].getName() + sortedBatsmanArray[0].getAverage());
		assertEquals("David Warner", sortedBatsmanArray[0].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_BowlerWith_BestBowlingAverages() {
		String sortedBowlerData = ipl_LeagueAnalyser.getMaximumBowlingAverageCricketers();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBowlerData, Bowler[].class);
		System.out.println(sortedBowlerArray[0].getName() + sortedBowlerArray[0].getAverage());
		assertEquals("Anukul Roy", sortedBowlerArray[0].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_BowlerWith_BestBowlingStrikeRates() {
		String sortedBowlerData = ipl_LeagueAnalyser.getMaximumBowlingStrikeRatesCricketers();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBowlerData, Bowler[].class);
		System.out.println(sortedBowlerArray[0].getName() + sortedBowlerArray[0].getStrikeRate());
		assertEquals("Alzarri Joseph", sortedBowlerArray[0].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_BowlerWith_BestEconomy() {
		String sortedBowlerData = ipl_LeagueAnalyser.getBestEconomyRateBowler();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBowlerData, Bowler[].class);
		System.out.println(sortedBowlerArray[0].getName() + sortedBowlerArray[0].getEconomy());
		assertEquals("Shivam Dube", sortedBowlerArray[0].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_BowlerWith_BestBowlingStrikeRatesAndWicketHauls() {
		String sortedBowlerData = ipl_LeagueAnalyser.getMaximumBowlingStrikeRatesCricketers();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBowlerData, Bowler[].class);
		assertEquals("Alzarri Joseph", sortedBowlerArray[0].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_BestBowlingAverages_WithBestStrikeRate() {
		String sortedBowlerData = ipl_LeagueAnalyser.getMaximumBowlingAverageCricketers();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBowlerData, Bowler[].class);
		assertEquals("Anukul Roy", sortedBowlerArray[0].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_MaximumWickets_WithBestBowlingAverages() {
		String sortedBowlerData = ipl_LeagueAnalyser.getCricketerWithMaximumWickets();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBowlerData, Bowler[].class);
		System.out.println(sortedBowlerArray[0].getName() + sortedBowlerArray[0].getWicketsTaken());
		assertEquals("Imran Tahir", sortedBowlerArray[0].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_BattingAndBowlingAverages() {
		String sortedBowlerData = ipl_LeagueAnalyser.getBattingAndBowlingAveragesCricketers();
		AllRounder[] sortedAllRounderArray = new Gson().fromJson(sortedBowlerData, AllRounder[].class);
		System.out.println(sortedAllRounderArray[1].getName() + sortedAllRounderArray[1].getBattingAverage()
				+ sortedAllRounderArray[1].getBowlingAverage());
		assertEquals("Andre Russell", sortedAllRounderArray[0].getName());
		assertEquals("Kagiso Rabada", sortedAllRounderArray[1].getName());
	}

	@Test
	public void givenCSVFile_shouldReturn_AllRoundersWith_MostRunsAndWickets() {
		String sortedAllRounderData = ipl_LeagueAnalyser.getCricketerWithMostRunsAndWickets();
		AllRounder[] sortedAllRounderArray = new Gson().fromJson(sortedAllRounderData, AllRounder[].class);
		System.out.println(sortedAllRounderArray[0].getName() + sortedAllRounderArray[0].getRunsScored());
		assertEquals("Andre Russell", sortedAllRounderArray[0].getName());
		assertEquals("Kagiso Rabada", sortedAllRounderArray[1].getName());
	}
	
	@Test
	public void givenCSVFile_shouldReturn_CricketersWith_MaximumHundredsAnd_BestBattingAverages() {
		String sortedBatsmanData = ipl_LeagueAnalyser.getMaximumHundredsCricketers();
		Bowler[] sortedBowlerArray = new Gson().fromJson(sortedBatsmanData, Bowler[].class);
		assertEquals("David Warner", sortedBowlerArray[0].getName());
	}
}
