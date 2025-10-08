public class JackieData {
    int year, races, wins, podiums, poles, fastests;

    public JackieData(int fastests, int poles, int podiums, int wins, int year, int races) {
        this.fastests = fastests;
        this.poles = poles;
        this.podiums = podiums;
        this.wins = wins;
        this.year = year;
        this.races = races;
    }

    public int getYear() {
        return year;
    }

    public int getRaces() {
        return races;
    }

    public int getPodiums() {
        return podiums;
    }

    public int getWins() {
        return wins;
    }

    public int getPoles() {
        return poles;
    }

    public int getFastests() {
        return fastests;
    }

    @Override
    public String toString() {
        return "JackieData{" +
                "year=" + year +
                ", races=" + races +
                ", wins=" + wins +
                ", podiums=" + podiums +
                ", poles=" + poles +
                ", fastests=" + fastests +
                '}';
    }
}
