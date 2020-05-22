package com.formationandroidkt.introduction.operations

class CommandeMult(override var listeNombres: List<Int>) : Calculable
{

	companion object
	{
		// Constante :
		const val OPERATION = "mult"
	}

	override fun getOperation(): String = OPERATION

	override fun calculer(): Int
	{
		var total = 1
		listeNombres.forEach {
			total *= it
		}
		return total
	}

}