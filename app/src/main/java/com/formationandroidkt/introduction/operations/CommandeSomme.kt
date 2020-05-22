package com.formationandroidkt.introduction.operations

class CommandeSomme(override var listeNombres: List<Int>) : Calculable
{

	companion object
	{
		// Constante :
		const val OPERATION = "somme"
	}

	override fun getOperation(): String = OPERATION

	override fun calculer(): Int
	{
		var total = 0
		listeNombres.forEach {
			total += it
		}
		return total
	}

}