package com.example.lab05;

public class Player {
    private int rank;
    private int score;
    private int rating;
    private String username;

    Player(int rank, int score, int rating, String username){
        this.rank = rank;
        this.score = score;
        this.rating = rating;
        this.username = username;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Player{" +
                "rank=" + rank +
                ", score=" + score +
                ", rating=" + rating +
                ", username='" + username + '\'' +
                '}';
    }
}
