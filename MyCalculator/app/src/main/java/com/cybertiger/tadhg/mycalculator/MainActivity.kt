package com.cybertiger.tadhg.mycalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun btnNumberEvent(view: View){
        if(isNewOp)
            showOperation.setText("")
        isNewOp=false
        val btnSelect = view as Button
        var btnClickValue:String=showOperation.text.toString()
        when(btnSelect.id){
            btn0.id->{
                btnClickValue+="0"
            }
            btn1.id->{
                btnClickValue+="1"
            }
            btn2.id->{
                btnClickValue+="2"
            }
            btn3.id->{
                btnClickValue+="3"
            }
            btn4.id->{
                btnClickValue+="4"
            }
            btn5.id->{
                btnClickValue+="5"
            }
            btn6.id->{
                btnClickValue+="6"
            }
            btn7.id->{
                btnClickValue+="7"
            }
            btn8.id->{
                btnClickValue+="8"
            }
            btn9.id->{
                btnClickValue+="9"
            }
            btnDecimal.id->{
                //TODO: Prevent adding more than 1 dot
                btnClickValue+="."
            }
            btnSign.id->{
                btnClickValue="-"+btnClickValue
            }
        }
        showOperation.setText(btnClickValue)
    }
    var op="*"
    var oldNumber=""
    var isNewOp=true
    fun btnOperationEvent(view:View){
        val btnSelect = view as Button
        when(btnSelect.id) {
            btnMulti.id -> {
                op="*"
            }
            btnDivide.id -> {
                op="/"
            }
            btnSub.id -> {
                op="-"
            }
            btnAdd.id -> {
                op="+"
            }
        }
        oldNumber=showOperation.text.toString()
        isNewOp=true
    }
    fun btnEqualEvent(view: View){
        val newNumber=showOperation.text.toString()
        var finalNumber:Double?=null
        when(op){
            "*"->{
                finalNumber= oldNumber.toDouble() * newNumber.toDouble()
            }
            "/"->{
                finalNumber= oldNumber.toDouble() / newNumber.toDouble()
            }
            "-"->{
                finalNumber= oldNumber.toDouble() - newNumber.toDouble()
            }
            "+"->{
                finalNumber= oldNumber.toDouble() + newNumber.toDouble()
            }
        }
        showOperation.setText(finalNumber.toString())
        isNewOp=true
    }
    fun btnPercentEvent(view:View){
        val number:Double=showOperation.text.toString().toDouble()/100
        showOperation.setText(number.toString())
        isNewOp=true
    }
    fun btnCleanEvent(view:View){
        showOperation.setText("0")
        isNewOp=true
    }
}
