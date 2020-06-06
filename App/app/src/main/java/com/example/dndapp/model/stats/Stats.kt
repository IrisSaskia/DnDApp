package com.example.dndapp.model.stats

abstract class Stat {
    abstract var characterID: Long?
    abstract var stat: Int
    abstract var modifier: Int
    abstract var save: Int
}

//TODO: als ik hier niet uitkom kan deze class weg en de inheritence ervan ook