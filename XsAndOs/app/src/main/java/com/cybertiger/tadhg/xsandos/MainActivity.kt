package com.cybertiger.tadhg.xsandos

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    protected fun buttonClick(view: View){
        val btnSelected = view as Button
        var cellID=0
        when(btnSelected.id){
            R.id.btnOne-> cellID = 1
            R.id.btnTwo-> cellID = 2
            R.id.btnThree-> cellID = 3
            R.id.btnFour-> cellID = 4
            R.id.btnFive-> cellID = 5
            R.id.btnSix-> cellID = 6
            R.id.btnSeven-> cellID = 7
            R.id.btnEight-> cellID = 8
            R.id.btnNine-> cellID = 9
        }
        playRound(cellID, btnSelected)
    }
    var playerOne = ArrayList<Int>()
    var playerTwo = ArrayList<Int>()
    var activePlayer = 1

    fun playRound(cellID:Int, btnSelected:Button){
        if(activePlayer==1){
            btnSelected.text="X"
            btnSelected.setBackgroundColor(Color.GREEN)
            playerOne.add(cellID)
            activePlayer=2
            autoPlay()
        }else{
            btnSelected.text="O"
            btnSelected.setBackgroundColor(Color.BLUE)
            playerTwo.add(cellID)
            activePlayer=1
        }
        btnSelected.isEnabled=false
        checkWinner()
    }

    fun checkWinner(){
        var winner =-1
        //====================================Rows====================================
        //Row 1
        if(playerOne.contains(1) && playerOne.contains(2) && playerOne.contains(3))
            winner = 1
        if(playerTwo.contains(1) && playerTwo.contains(2) && playerTwo.contains(3))
            winner = 2

        //Row 2
        if(playerOne.contains(4) && playerOne.contains(5) && playerOne.contains(6))
            winner = 1
        if(playerTwo.contains(4) && playerTwo.contains(5) && playerTwo.contains(6))
            winner = 2

        //Row 3
        if(playerOne.contains(7) && playerOne.contains(8) && playerOne.contains(9))
            winner = 1
        if(playerTwo.contains(7) && playerTwo.contains(8) && playerTwo.contains(9))
            winner = 2


        //=================================Columns====================================
        //Col 1
        if(playerOne.contains(1) && playerOne.contains(4) && playerOne.contains(7))
            winner = 1
        if(playerTwo.contains(1) && playerTwo.contains(4) && playerTwo.contains(7))
            winner = 2

        //Col 2
        if(playerOne.contains(2) && playerOne.contains(5) && playerOne.contains(8))
            winner = 1
        if(playerTwo.contains(2) && playerTwo.contains(5) && playerTwo.contains(8))
            winner = 2

        //Col 3
        if(playerOne.contains(3) && playerOne.contains(6) && playerOne.contains(9))
            winner = 1
        if(playerTwo.contains(3) && playerTwo.contains(6) && playerTwo.contains(9))
            winner = 2

        //================================Diagonal====================================
        if(playerOne.contains(1) && playerOne.contains(5) && playerOne.contains(9))
            winner = 1
        if(playerTwo.contains(1) && playerTwo.contains(5) && playerTwo.contains(9))
            winner = 2

        //Col 2
        if(playerOne.contains(3) && playerOne.contains(5) && playerOne.contains(7))
            winner = 1
        if(playerTwo.contains(3) && playerTwo.contains(5) && playerTwo.contains(7))
            winner = 2

        if(winner != -1){
            if(winner == 1) {
                Toast.makeText(this, "Player 1 has won the game", Toast.LENGTH_LONG).show()
            }else {
                Toast.makeText(this, "Player 2 has won the game", Toast.LENGTH_LONG).show()
            }
        }

    }

    fun autoPlay(){
        var emptyCells=ArrayList<Int>()
        for(cellID in 1..9){
            if(!(playerOne.contains(cellID) || playerTwo.contains(cellID))){
                emptyCells.add(cellID)
            }
        }

        val r = Random()
        val randomIndex=r.nextInt(emptyCells.size-0)+0
        val cellID= emptyCells[randomIndex]

        var btnSelect:Button?
        when(cellID){
            1 -> btnSelect=btnOne
            2 -> btnSelect=btnTwo
            3 -> btnSelect=btnThree
            4 -> btnSelect=btnFour
            5 -> btnSelect=btnFive
            6 -> btnSelect=btnSix
            7 -> btnSelect=btnSeven
            8 -> btnSelect=btnEight
            9 -> btnSelect=btnNine
            else->{
                btnSelect=btnOne
            }
        }

        playRound(cellID, btnSelect)
    }
}
