package com.beust.nnk

import com.beust.jcommander.JCommander
import com.beust.jcommander.Parameter

var LOG_LEVEL = 1

fun log(level: Int, s: String) {
    if (LOG_LEVEL >= level) println(s)
}

fun main(argv: Array<String>) {
    class Args {
        @Parameter(names = arrayOf("--log"), description = "Define the log level (1-3)")
        var log: Int = 1
    }

    val args = Args()
    JCommander(args).parse(*argv)
    LOG_LEVEL = args.log

    println("LOG LEVEL IS " + LOG_LEVEL)
    log(1, "Running neural network xor()")
    xor()

    log(1, "Running neural network isOdd()")
    isOdd()

    log(1, "Running neural network nand()")
    nand()

    log(1, "Running neural network implication()")
    implication()
}

fun isOdd() {
    with(NeuralNetwork(inputSize = 4, hiddenSize = 2, outputSize = 1)) {
        val trainingValues = listOf(
            NetworkData.create(listOf(0, 0, 0, 0), listOf(0)),
            NetworkData.create(listOf(0, 0, 0, 1), listOf(1)),
            NetworkData.create(listOf(0, 0, 1, 0), listOf(0)),
            NetworkData.create(listOf(0, 1, 1, 0), listOf(0)),
            NetworkData.create(listOf(0, 1, 1, 1), listOf(1)),
            NetworkData.create(listOf(1, 0, 1, 0), listOf(0)),
            NetworkData.create(listOf(1, 0, 1, 1), listOf(1)),
            NetworkData.create(listOf(1, 1, 0, 0), listOf(0)),
            NetworkData.create(listOf(1, 1, 0, 1), listOf(1)),
            NetworkData.create(listOf(1, 1, 1, 0), listOf(0)),
            NetworkData.create(listOf(1, 1, 1, 1), listOf(1))
        )
        train(trainingValues)

        val testValues = listOf(
            NetworkData.create(listOf(0, 0, 1, 1), listOf(1)),
            NetworkData.create(listOf(0, 1, 0, 0), listOf(0)),
            NetworkData.create(listOf(0, 1, 0, 1), listOf(1)),
            NetworkData.create(listOf(1, 0, 0, 0), listOf(0)),
            NetworkData.create(listOf(1, 0, 0, 1), listOf(1))
        )
        test(testValues)

        dump()
    }
}

fun xor() {
    with(NeuralNetwork(inputSize = 2, hiddenSize = 2, outputSize = 1)) {
        val trainingValues = listOf(
            NetworkData.create(listOf(0, 0), listOf(0)),
            NetworkData.create(listOf(0, 1), listOf(1)),
            NetworkData.create(listOf(1, 0), listOf(1)),
            NetworkData.create(listOf(1, 1), listOf(0)))
        train(trainingValues)
        test(trainingValues)
    }
}

fun nand() {
    with(NeuralNetwork(inputSize = 2, hiddenSize = 2, outputSize = 1)) {
        val trainingValues = listOf(
                NetworkData.create(listOf(0, 0), listOf(1)),
                NetworkData.create(listOf(0, 1), listOf(1)),
                NetworkData.create(listOf(1, 0), listOf(1)),
                NetworkData.create(listOf(1, 1), listOf(0)))
        train(trainingValues)
        test(trainingValues)
    }
}

fun implication() {
    with(NeuralNetwork(inputSize = 2, hiddenSize = 2, outputSize = 1)) {
        val trainingValues = listOf(
                NetworkData.create(listOf(0, 0), listOf(1)),
                NetworkData.create(listOf(0, 1), listOf(1)),
                NetworkData.create(listOf(1, 0), listOf(0)),
                NetworkData.create(listOf(1, 1), listOf(1)))
        train(trainingValues)
        test(trainingValues)
    }
}