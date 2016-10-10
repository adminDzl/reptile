package com.reptile.common.logic.entity;

import java.util.Date;

public class TMovieData {
    private Integer id;

    private Date createTime;

    private Date updateTime;

    private Double douBanVal;

    private String movieActor;

    private String movieDownLoadSourceUrl;

    private String movieImgUrl;

    private String movieName;

    private String movieNation;

    private String movieSourceUrl;

    private Integer releaseYear;

    private Integer grabPlatfrom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Double getDouBanVal() {
        return douBanVal;
    }

    public void setDouBanVal(Double douBanVal) {
        this.douBanVal = douBanVal;
    }

    public String getMovieActor() {
        return movieActor;
    }

    public void setMovieActor(String movieActor) {
        this.movieActor = movieActor == null ? null : movieActor.trim();
    }

    public String getMovieDownLoadSourceUrl() {
        return movieDownLoadSourceUrl;
    }

    public void setMovieDownLoadSourceUrl(String movieDownLoadSourceUrl) {
        this.movieDownLoadSourceUrl = movieDownLoadSourceUrl == null ? null : movieDownLoadSourceUrl.trim();
    }

    public String getMovieImgUrl() {
        return movieImgUrl;
    }

    public void setMovieImgUrl(String movieImgUrl) {
        this.movieImgUrl = movieImgUrl == null ? null : movieImgUrl.trim();
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName == null ? null : movieName.trim();
    }

    public String getMovieNation() {
        return movieNation;
    }

    public void setMovieNation(String movieNation) {
        this.movieNation = movieNation == null ? null : movieNation.trim();
    }

    public String getMovieSourceUrl() {
        return movieSourceUrl;
    }

    public void setMovieSourceUrl(String movieSourceUrl) {
        this.movieSourceUrl = movieSourceUrl == null ? null : movieSourceUrl.trim();
    }

    public Integer getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Integer releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Integer getGrabPlatfrom() {
        return grabPlatfrom;
    }

    public void setGrabPlatfrom(Integer grabPlatfrom) {
        this.grabPlatfrom = grabPlatfrom;
    }
}