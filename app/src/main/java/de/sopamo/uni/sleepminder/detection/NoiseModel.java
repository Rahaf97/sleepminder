package de.sopamo.uni.sleepminder.detection;

import java.util.ArrayList;
import java.util.List;

public class NoiseModel {
    private List<Double> RMS;
    private List<Double> RLH;
    private List<Double> VAR;

    public NoiseModel() {
        RMS = new ArrayList<>();
        RLH = new ArrayList<>();
        VAR = new ArrayList<>();
    }

    public void addRMS(Double rms) {
        if(RMS.size() >= 40) {
            RMS.remove(0);
        }
        RMS.add(rms);
    }
    public void addRLH(Double rlh) {
        if(RLH.size() >= 40) {
            RLH.remove(0);
        }
        RLH.add(rlh);
    }
    public void addVAR(Double var) {
        if(VAR.size() >= 40) {
            VAR.remove(0);
        }
        VAR.add(var);
    }

    public double getNormalizedRMS() {
        if(RMS.size() == 0) return 0d;

        return (RMS.get(RMS.size()-1) - mean(RMS)) / std(RMS);
    }

    public double getNormalizedRLH() {
        if(RLH.size() == 0) return 0d;

        return (RLH.get(RLH.size()-1) - mean(RLH)) / std(RLH);
    }

    public double getNormalizedVAR() {
        if(VAR.size() == 0) return 0d;

        return (VAR.get(VAR.size()-1) - mean(VAR)) / std(VAR);
    }

    private double mean(List<Double> list) {
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum / list.size();
    }

    private double std(List<Double> list) {
        double mean = mean(list);
        double var = 0;
        for(int i = 0; i < list.size(); i++) {
            var += Math.pow(list.get(i) - mean,2);
        }
        return Math.sqrt(var / list.size());
    }

}
