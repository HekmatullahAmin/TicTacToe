fun main() {
    var board = arrayListOf<ArrayList<Char>>()
    for (i in 0..2) {
        var row = arrayListOf<Char>()
        for (j in 0..2) {
            row.add(' ')
        }
        board.add(row)
    }
    printBoard(board)

    var player = 1
    do {
        println("$player Player -> Please enter a position (e.g. 1,1)")
        var input = readLine() ?: ""
        var x = 0
        var y = 0
        try {
            var position = input.split(",")
            x = position[0].trim().toInt()
            y = position[1].trim().toInt()
        } catch (ex: Exception) {
            println("Invalid input please try again")
            continue
        }

//        To check if the selected position is allocated or not
        if (board[x][y] == ' ') {
            if (player == 1) {
                board[x][y] = 'X'
                player = 2
            } else {
                board[x][y] = 'O'
                player = 1
            }
        } else {
            continue
        }

        var winner = verifyTheWinner(board)
//        do not terminate game if we have empty cell in every row
//        and terminate if the player win before filling all cells
        if (!checkForEmptyCell(board) || winner == 'X' ?: 'Y') {
            if (winner == 'N') {
                println("The game is tie")
            } else {
                println("The winner is $winner player")
            }
            printBoard(board)
            break
        }
        printBoard(board)
    } while (true)
}

fun printBoard(board: ArrayList<ArrayList<Char>>) {
    for (i in board.indices) {
        println("-------------")//13
        for (j in board.indices) {
            print("| " + board[i][j] + " ")
        }
        println("|")
    }
}

fun checkForEmptyCell(board: ArrayList<ArrayList<Char>>): Boolean {
    var isThereEmptyCell = false
    for (i in board.indices) {
        for (j in board.indices) {
            if (board[i][j] == ' ') {
                isThereEmptyCell = true
                break
            }
        }
    }
    return isThereEmptyCell
}

fun verifyTheWinner(board: ArrayList<ArrayList<Char>>): Char {

    return if (board[0][0] == board[0][1] && board[0][1] == board[0][2])
        board[0][0]
    else if (board[1][0] == board[1][1] && board[1][1] == board[1][2])
        board[1][0]
    else if (board[2][0] == board[2][1] && board[2][1] == board[2][2])
        board[2][0]
//        base on column
    else if (board[0][0] == board[1][0] && board[1][0] == board[2][0])
        board[0][0]
    else if (board[0][1] == board[1][1] && board[1][1] == board[2][1])
        board[0][1]
    else if (board[0][2] == board[1][2] && board[1][2] == board[2][2])
        board[0][2]
//        base on diagonals
    else if (board[0][0] == board[1][1] && board[1][1] == board[2][2])
        board[0][0]
    else if (board[0][2] == board[1][1] && board[1][1] == board[2][0])
        board[0][2]
    else
        'N'
}