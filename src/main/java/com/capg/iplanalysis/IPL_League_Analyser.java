package com.capg.iplanalysis;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import com.google.gson.Gson;

public class IPL_League_Analyser {
	private List<Batsman> batsmanList = null;
	private List<Bowler> bowlerList = null;

	public void loadBatsmanData(String PATH) throws IPLAnalyserException {
		batsmanList = new CSVBatsman().loadBatsmanList(PATH);
	}

	public void loadBowlerData(String BOWLER_CSV_PATH) throws IPLAnalyserException {
		bowlerList = new CSVBowler().loadBowlerList(BOWLER_CSV_PATH);
	}

	public String getBestBattingAveragesCricketers() { // UC1->UC5
		List<Batsman> sortedStateBatsmanList = batsmanList.stream()
				.sorted(Comparator.comparing(Batsman::getAverage).thenComparing(Batsman::getStrikeRate).reversed())
				.collect(Collectors.toList());
		return toJson(sortedStateBatsmanList);
	}

	public <E> String toJson(List<E> list) {
		return new Gson().toJson(list);
	}

	public String getBestStrikeRateCricketers() { // UC2->UC4
		List<Batsman> sortedStateBatsmanList = batsmanList.stream()
				.sorted(Comparator.comparing(Batsman::getStrikeRate).thenComparing(Batsman::getBoundries).reversed())
				.collect(Collectors.toList());
		return toJson(sortedStateBatsmanList);
	}

	public String getMaximumBoundriesCricketers() {
		List<Batsman> sortedStateBatsmanList = batsmanList.stream()
				.sorted(Comparator.comparing(Batsman::getBoundries).reversed()).collect(Collectors.toList());
		return toJson(sortedStateBatsmanList);
	}

	public String getMaximumRunsCricketers() {
		List<Batsman> sortedStateBatsmanList = batsmanList.stream()
				.sorted(Comparator.comparing(Batsman::getRunsScored).thenComparing(Batsman::getAverage).reversed())
				.collect(Collectors.toList());
		return toJson(sortedStateBatsmanList);
	}

	public String getMaximumBowlingAverageCricketers() {
		List<Bowler> sortedBowlerList = bowlerList.stream().filter(n -> n.getAverage() > 0)
				.sorted(Comparator.comparing(Bowler::getAverage)).collect(Collectors.toList());
		return toJson(sortedBowlerList);
	}

	public String getMaximumBowlingStrikeRatesCricketers() {
		List<Bowler> sortedBowlerList = bowlerList.stream().filter(n -> n.getStrikeRate() > 0)
				.sorted(Comparator.comparing(Bowler::getStrikeRate).thenComparing(Bowler::getHauls))
				.collect(Collectors.toList());
		return toJson(sortedBowlerList);
	}

	public String getBestEconomyRateBowler() {
		List<Bowler> sortedBowlerList = bowlerList.stream().sorted(Comparator.comparing(Bowler::getStrikeRate))
				.collect(Collectors.toList());
		return toJson(sortedBowlerList);
	}

	public String getCricketerWithMaximumWickets() {
		List<Bowler> sortedBowlerList = bowlerList.stream().filter(n -> n.getStrikeRate() > 0)
				.sorted(Comparator.comparing(Bowler::getWicketsTaken).thenComparing(Bowler::getAverage).reversed())
				.collect(Collectors.toList());
		return toJson(sortedBowlerList);
	}

	public String getBattingAndBowlingAveragesCricketers() {
		List<AllRounder> allRounderList = new ArrayList<AllRounder>();
		Map<String, Batsman> map = new HashMap<String, Batsman>();
		Map<String, Bowler> map2 = new HashMap<String, Bowler>();
		batsmanList.forEach(n -> map.put(n.getName(), n));
		bowlerList.forEach(n -> map2.put(n.getName(), n));
		
		map.forEach((k,v)-> {
						if(map2.containsKey(k)) {
							AllRounder a = new AllRounder();
							a.setName(k);
							a.setBattingAverage(v.getAverage());
							a.setBowlingAverage(map2.get(k).getAverage());
							allRounderList.add(a);
						}
				});
		List<AllRounder> battingAllRounderList = allRounderList.stream()
				.filter(n -> n.getBowlingAverage() > 0 & n.getBattingAverage() > 0)
					  .sorted(Comparator.comparing(AllRounder::getBattingAverage).reversed())
					  .collect(Collectors.toList());
		System.out.println(battingAllRounderList);
		
		
		List<AllRounder> bowlingAllRounderList = allRounderList.stream()
				.filter(n -> n.getBowlingAverage() > 0 & n.getBattingAverage() > 0)
				  .sorted(Comparator.comparing(AllRounder::getBowlingAverage))
				  .collect(Collectors.toList());
		List<AllRounder> sortedAllRounderList = new ArrayList<>();
		sortedAllRounderList.add(battingAllRounderList.get(0)); 
		sortedAllRounderList.add(bowlingAllRounderList.get(0));
		return toJson(sortedAllRounderList);
	}
	
}


