package com.capg.iplanalysis;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import com.google.gson.Gson;

public class IPL_League_Analyser {
	private List<Batsman> batsmanList = null;
	public void loadBatsmanData(String PATH) throws IPLAnalyserException {
		batsmanList = new CSVBatsman().loadBatsmanList(PATH);
	}

	public String getBestBattingAveragesCricketers() {                      //UC1->UC5
		List<Batsman> sortedStateBatsmanList = batsmanList.stream()
				.sorted(Comparator.comparing(Batsman::getAverage).thenComparing(Batsman::getStrikeRate).reversed()).collect(Collectors.toList());
		return toJson(sortedStateBatsmanList);
	}

	public <E> String toJson(List<E> list) {
		return new Gson().toJson(list);
	}

	public String getBestStrikeRateCricketers() {                            //UC2->UC4
		List<Batsman> sortedStateBatsmanList = batsmanList.stream()
				.sorted(Comparator.comparing(Batsman::getStrikeRate).thenComparing(Batsman::getBoundries).reversed()).collect(Collectors.toList());
		return toJson(sortedStateBatsmanList);
	}

	public String getMaximumBoundriesCricketers() {
		List<Batsman> sortedStateBatsmanList = batsmanList.stream()
				.sorted(Comparator.comparing(Batsman :: getBoundries).reversed()).collect(Collectors.toList());
		return toJson(sortedStateBatsmanList);
	}

	public String getMaximumRunsCricketers() {
		List<Batsman> sortedStateBatsmanList = batsmanList.stream()
				.sorted(Comparator.comparing(Batsman::getRunsScored).thenComparing(Batsman::getAverage).reversed())
				.collect(Collectors.toList());
		return toJson(sortedStateBatsmanList);
	}

}
