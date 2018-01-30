package kshell

import kshell.console.Completer

abstract class BaseCommand(private val fullName: String,
                           private val shortName: String,
                           private val description: String,
                           private val ignoreCase: Boolean = true): Command {

    open val params = ""

    override fun match(line: String): Boolean {
        val ind = line.indexOf(' ')
        val command = if (ind < 0) line else line.substring(0, ind)
        return command.startsWith(":$shortName", ignoreCase = ignoreCase) ||
                command.startsWith(":$fullName", ignoreCase = ignoreCase)
    }

    override fun help(): String = "(no extended help available)"

    override fun desc(): String = String.format("%-30s %s",  ":$fullName or $shortName $params", description)

    override fun completer(): Completer = Completer.DEFAULT_COMPLETER
}