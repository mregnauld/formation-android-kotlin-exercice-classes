package com.formationandroidkt.introduction.operations

class CommandeDiff(override var listeNombres: List<Int>) : Calculable
{

	companion object
	{
		// Constante :
		const val OPERATION = "diff"
	}

	override fun getOperation(): String = OPERATION

	override fun calculer(): Int
	{
		var total = listeNombres[0]
		listeNombres.takeLast(listeNombres.size - 1).forEach {
			total -= it
		}
		return total
	}

}