fun main() {
    Game.play()
}

object Game {
    private var isRunning = true
    private var isBattle = true
    private var player = Player()
    private var enemyList = generateEnemys(10)
    private var enemyIndex = 0

    init {
        player = Player("player", randomInt(1, 20), randomInt(1, 20),
            randomInt(0, 100), randomRange(0, 20))
    }

    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1) { "" }

        fun processMenuCommand() = when(command.lowercase()) {
            "fight" -> fight(argument)
            "stat" -> playerStats()
            "all" -> printAllEnemysStats()
            "quit" -> quit("Bye")
            else -> "Unknown command"
        }

        fun processBattleCommand() = when(command.lowercase()) {
            "attack" -> attack()
            "heal" -> useHeal()
            else -> "Unknown command"
        }
    }

    private fun playerStats(): String {
        return player.statistic()
    }

    private fun quit(message: String): String {
        isRunning = false
        isBattle = false
        return message
    }

    private fun fight(_enemyIndex: String): String {
        return try {
            enemyIndex = _enemyIndex.toInt()

            while (isBattle) {
                println("Enemy stats: ${enemyList[enemyIndex].statistic()}")
                println("Player stats: ${player.statistic()}")
                print("Your turn\nEnter you command: ")
                println(GameInput(readLine()).processBattleCommand())
            }
            isBattle = true
            "Battle end"
        } catch (e: Exception) {
            e.toString()
        }
    }

    private fun attack(): String {
        val enemy = enemyList[enemyIndex]
        println("You dealt ${player.attackEntity(enemy)}hp of damage")

        if(enemy.isAlive()) {
            println("Opponent's turn\nYou have taken ${enemy.attackEntity(player)}hp of damage")
        }
        else {
            isBattle = false
            enemyList.removeAt(enemyIndex)
            return "You win"
        }

        if(!player.isAlive()) {
            return quit("You lose")
        }

        return "The fight goes on"
    }

    private fun useHeal(): String {
        player.useHeal()
        return "You used heal. Now your HP = ${player._healthPoint}"
    }

    private fun printAllEnemysStats(): String {
        enemyList.forEachIndexed { index, element -> println("$index) ${element.statistic()}") }
        return  ""
    }

    fun play() {
        printAllEnemysStats()
        while(isRunning) {
            print("Enter you command: ")
            println(GameInput(readLine()).processMenuCommand())
        }
    }

    private fun generateEnemys(count: Int): MutableList<Monster> {
        val list = mutableListOf<Monster>()

        for (i in 0..count) {
            try {
                list.add(Monster("ork", randomInt(1, 20), randomInt(1, 20),
                    randomInt(0, 100), randomRange(0, 20)))
            } catch (e: Exception) {
                println(e.toString())
            }
        }

        return list
    }

    private fun randomRange(leftBound: Int, rightBound: Int): IntRange {
        val left = (leftBound..rightBound).random()
        return left..randomInt(left, rightBound)
    }

    private fun randomInt(leftBound: Int, rightBound: Int): Int {
        return (leftBound..rightBound).random()
    }
}