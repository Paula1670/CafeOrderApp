/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package iia.dsl.Tareas;

import iia.dsl.Slot;

/**
 *
 * @author Manuel
 */
public class Task {

    private Slot input, output;

    public Task() {
    }

    public Task(Slot input) {
        this.input = input;
    }

    public Task(Slot input, Slot output) {
        this.input = input;
        this.output = output;
    }

    public Slot getInput() {
        return input;
    }

    public void setInput(Slot input) {
        this.input = input;
    }

    public Slot getOutput() {
        return output;
    }

    public void setOutput(Slot output) {
        this.output = output;
    }

}
