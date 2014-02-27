import scala.collection.mutable.ArrayBuffer
import scala.collection.mutable.ListBuffer

object Main {
  def main(args: Array[String]) {

  	println("Enter your number for the magic square")
    val input = Integer.parseInt(Console.readLine)
    
    var magicSquare = ArrayBuffer.fill[Int](input*input)(0)

    if(input % 2 == 1){//odd
    	
    	var currentLocation = input/2 + 1//midPoint
    	var nextLocation = currentLocation

    	for (i <- 1 to input*input){

            magicSquare(nextLocation-1) = i

            currentLocation = nextLocation
            //find next locations
            //Go up 1 row
            if(nextLocation <= input){
                nextLocation += (input-1) * input //Go to the bottom row
            } else {
                nextLocation -= input; // Go up 1 row
            }
            //Move 1 to the right
            if(nextLocation % input == 0){
                nextLocation -= (input-1)
            } else{
                nextLocation +=1
            }

            //if spot is taken
            if(!(magicSquare(nextLocation-1) == 0)){
                nextLocation = currentLocation
                //do current location down 1 row
                if(currentLocation <= (input*(input-1))){
                    nextLocation += input
                } else {
                    nextLocation -= (input * (input - 1))
                }
            }    	
        }
        printSquare(magicSquare)

    }//end of odd solution
     if (input % 4 == 0){//doubly even
        
        var tops = (input*.25).toInt
        var mids = input - tops*2;

        var start = (input*.25).toInt
        var end = start+input/2
        start += 1

        var topBase = ListBuffer.empty[Int]
        var midBase = ListBuffer.empty[Int]
        var myList = ListBuffer.empty[Int]

        for( x <- start to end) {
            topBase += x
        }

        for( i <- 1 to input) {
            if(!topBase.contains(i)){
                midBase += i
            }
        }

        for( i <- 0 to tops-1) {
            for(a <- topBase){
                myList += (a+(input*i))
            }
        }

        for( i <- tops to tops+mids-1) {
            for(a <- midBase){
                myList += (a+(input*i))
            }
        }

        for(i <- (tops+mids) to (tops+mids-1+tops)){
            for(a <- topBase){
                myList += (a+(input*i))
            }
        }
        
        //myList buffer is full at this point
        var decrementer = input*input;
        for( i <- 1 to input*input) {
            if(myList.contains(i)){
                magicSquare(i-1)=i
            } else {
                magicSquare(i-1)=decrementer
            }
            decrementer-=1
        }
        printSquare(magicSquare)
    }
  }
}

def printSquare(x: ArrayBuffer[Int]) {
   var size = (Math.sqrt(x.length)).toInt
   println("size: "+size)
   var line = ""
   for(i <- 0 until size*size) {
      if(i % size == 0 && i != 0) line +="\n"
      line += x(i) + " "
   }
   println(line)
}

Main.main(null)
