package com.example.dndapp.model.stats

abstract class Stat {
    abstract var characterID: Long?
    abstract var stat: Int
    abstract var modifier: Int
    abstract var save: Int
}