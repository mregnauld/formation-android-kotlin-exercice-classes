package com.formationandroidkt.introduction.operations

interface Calculable
{

	// Nombres saisis :
	var listeNombres: List<Int>


	/**
	 * Retourne l'opération.
	 */
	fun getOperation(): String

	/**
	 * Calcul.
	 */
	fun calculer(): Int

}