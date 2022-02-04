package com.taurunium.pokemon.java.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PokemonDetails {
    private String name;
    private int id;
    private List<Type> types;
    private List<Stat> stats;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Type> getTypes() {
        return types;
    }

    public void setTypes(List<Type> types) {
        this.types = types;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }

    @Override
    public String toString() {
        return "PokemonDetails{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", types=" + types +
                ", stats=" + stats +
                '}';
    }
}
