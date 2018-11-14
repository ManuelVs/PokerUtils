package hja.pokerutils.Algorithm;

public class Combos {
    public int royalFlush;
    public int straightFlush;
    public int fourOfAKind;
    public int fullHouse;
    public int flush;
    public int straight;
    public int threeOfAKind;
    public int twoPair;
    public int overPair;
    public int topPair;
    public int pocketPairBelowTopPair;
    public int middlePair;
    public int weakPair;
    public int noHandMade;

    public int gutshotDraw;
    public int openEndedDraw;
    public int flushDraw;

    public Combos() {
        this(0, 0, 0, 0, 0, 0,
                0, 0,
                0, 0, 0, 0, 0,
                0, 0, 0, 0);
    }

    public Combos(int royalFlush, int straightFlush, int fourOfAKind, int fullHouse, int flush,
                  int straight, int threeOfAKind, int twoPair,
                  int overPair, int topPair, int pocketPairBelowTopPair, int middlePair, int weakPair,
                  int noHandMade, int gutshotDraw, int openEndedDraw, int flushDraw) {
        this.royalFlush = royalFlush;
        this.straightFlush = straightFlush;
        this.fourOfAKind = fourOfAKind;
        this.fullHouse = fullHouse;
        this.flush = flush;
        this.straight = straight;
        this.threeOfAKind = threeOfAKind;
        this.twoPair = twoPair;
        this.overPair = overPair;
        this.topPair = topPair;
        this.pocketPairBelowTopPair = pocketPairBelowTopPair;
        this.middlePair = middlePair;
        this.weakPair = weakPair;
        this.noHandMade = noHandMade;
        this.gutshotDraw = gutshotDraw;
        this.openEndedDraw = openEndedDraw;
        this.flushDraw = flushDraw;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Royal flush   : " + royalFlush);
        sb.append(System.lineSeparator());

        sb.append("Straight flush: " + straightFlush);
        sb.append(System.lineSeparator());

        sb.append("FoK           : " + fourOfAKind);
        sb.append(System.lineSeparator());

        sb.append("Full House    : " + fullHouse);
        sb.append(System.lineSeparator());

        sb.append("Flush         : " + flush);
        sb.append(System.lineSeparator());

        sb.append("Straight      : " + straight);
        sb.append(System.lineSeparator());

        sb.append("ToK           : " + threeOfAKind);
        sb.append(System.lineSeparator());

        sb.append("Two Pair      : " + twoPair);
        sb.append(System.lineSeparator());

        sb.append("Over pair     : " + overPair);
        sb.append(System.lineSeparator());

        sb.append("Top  pair     : " + topPair);
        sb.append(System.lineSeparator());

        sb.append("PP b top pair : " + pocketPairBelowTopPair);
        sb.append(System.lineSeparator());

        sb.append("Middle pair   : " + middlePair);
        sb.append(System.lineSeparator());

        sb.append("Weak pair     : " + weakPair);
        sb.append(System.lineSeparator());

        sb.append("No hand made  : " + noHandMade);
        sb.append(System.lineSeparator());

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Combos)) return false;
        if (this == obj) return true;

        Combos other = (Combos) obj;
        return this.royalFlush == other.royalFlush
                && this.straightFlush == other.straightFlush
                && this.fourOfAKind == other.fourOfAKind
                && this.fullHouse == other.fullHouse
                && this.flush == other.flush
                && this.straight == other.straight
                && this.threeOfAKind == other.threeOfAKind
                && this.twoPair == other.twoPair
                && this.overPair == other.overPair
                && this.topPair == other.topPair
                && this.pocketPairBelowTopPair == other.pocketPairBelowTopPair
                && this.middlePair == other.middlePair
                && this.weakPair == other.weakPair
                && this.noHandMade == other.noHandMade;
    }
}