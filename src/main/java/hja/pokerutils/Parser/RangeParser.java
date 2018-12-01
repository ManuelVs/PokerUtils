package hja.pokerutils.Parser;

import hja.pokerutils.Card.CardFactory;
import hja.pokerutils.Card.Rank;
import hja.pokerutils.Range.Range;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RangeParser {
	
	public static Range parseRange(String ranges) {
		String[] stringArrayRanges = ranges.split(", *");
		
		ArrayList<Range> rangesArray = new ArrayList<>(stringArrayRanges.length);
		
		for (String sRange : stringArrayRanges) {
			Range range = parseSingleRange(sRange);
			if (range == null) return null;
			
			rangesArray.add(range);
		}
		
		return new Range(rangesArray);
	}
	
	private static Range parseSingleRange(String range) {
		if (range.isEmpty()) return new Range();
		
		Pattern simpleRange = Pattern.compile("(?<first>[23456789TJQKA])(?<second>[23456789TJQKA])(?<suited>[os]?)(?<range>\\+?)");
		Pattern linearRange = Pattern.compile(
			"(?<first>[23456789TJQKA])(?<second>[23456789TJQKA])(?<suited1>[os])" +
				"-" +
				"(?<third>[23456789TJQKA])(?<fourth>[23456789TJQKA])(?<suited2>[os])");
		
		Matcher matcher = simpleRange.matcher(range);
		if (matcher.matches()) {
			String firstCard = matcher.group("first");
			String secondCard = matcher.group("second");
			String suited = matcher.group("suited");
			String isRange = matcher.group("range");
			
			Rank firstRank = CardFactory.getRank(firstCard.charAt(0));
			Rank secondRank = CardFactory.getRank(secondCard.charAt(0));
			
			if (firstRank == secondRank) {
				if (!suited.isEmpty()) return null;
				
				boolean isPairRange = !isRange.isEmpty();
				return new Range(firstRank, isPairRange);
			}
			else {
				return new Range(firstRank, secondRank, suited.equals("s"), isRange.equals("+"));
			}
		}
		else {
			matcher = linearRange.matcher(range);
			
			if (matcher.matches()) {
				String firstCard = matcher.group("first");
				String secondCard = matcher.group("second");
				String thirdCard = matcher.group("third");
				String fourthCard = matcher.group("fourth");
				String suited1 = matcher.group("suited1");
				String suited2 = matcher.group("suited2");
				
				if (firstCard.equals(thirdCard) && suited1.equals(suited2)) {
					Rank firstRank = CardFactory.getRank(firstCard.charAt(0));
					Rank initialRank = CardFactory.getRank(secondCard.charAt(0));
					Rank lastRank = CardFactory.getRank(fourthCard.charAt(0));
					
					return new Range(firstRank, initialRank, lastRank, suited1.equals("s"));
				}
			}
		}
		
		return null;
	}
}
