package com.formationandroidkt.introduction

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.formationandroidkt.introduction.operations.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity()
{

	// Historique :
	private var listeCommandes: MutableList<Calculable> = ArrayList()
	private var index: Int = -1


	override fun onCreate(savedInstanceState: Bundle?)
	{
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
	}

	/**
	 * Listener clic bouton calculer.
	 */
	@Suppress("UNUSED_PARAMETER")
	fun clicCalculer(view: View)
	{
		// commande :
		val commande = edittext_commande.text.toString()

		// éléments de la commande :
		val elements = commande.split(" ")

		// vérification préalable :
		if (elements.size < 2)
		{
			Toast.makeText(this, R.string.main_commande_invalide, Toast.LENGTH_LONG).show()
			return
		}

		try
		{
			// liste nombres :
			val listeNombre: MutableList<Int> = ArrayList()
			elements.takeLast(elements.size - 1).forEach {
				listeNombre.add(it.toInt())
			}

			// cas :
			val calculable: Calculable
			when (elements[0])
			{
				CommandeSomme.OPERATION -> calculable = CommandeSomme(listeNombre)
				CommandeDiff.OPERATION -> calculable = CommandeDiff(listeNombre)
				CommandeMult.OPERATION -> calculable = CommandeMult(listeNombre)
				CommandeDiv.OPERATION -> calculable = CommandeDiv(listeNombre)
				else ->
				{
					Toast.makeText(this, R.string.main_commande_invalide, Toast.LENGTH_LONG).show()
					return
				}
			}

			// ajout historique si nouvelle commande (pour simplification, on suppose que c'est une nouvelle commande si on est à la fin de l'historique) :
			if (index == listeCommandes.size - 1)
			{
				index++
				listeCommandes.add(calculable)
			}

			// résultat :
			Toast.makeText(this, getString(R.string.main_resultat, calculable.calculer()), Toast.LENGTH_LONG).show()
		}
		catch (e: Exception)
		{
			Toast.makeText(this, R.string.main_commande_invalide, Toast.LENGTH_LONG).show()
		}
	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean
	{
		menuInflater.inflate(R.menu.main_menu, menu)
		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean
	{
		return when (item.itemId)
		{
			R.id.precedent ->
			{
				if (index > 0)
				{
					index--
					afficherCommande(listeCommandes[index])
				}
				true
			}
			R.id.suivant ->
			{
				if (index < listeCommandes.size - 1)
				{
					index++
					afficherCommande(listeCommandes[index])
				}
				true
			}
			else -> super.onOptionsItemSelected(item)
		}
	}

	private fun afficherCommande(calculable: Calculable)
	{
		// reconstruction de la commande :
		var commande = "${calculable.getOperation()} "
		calculable.listeNombres.forEach {
			commande += "$it "
		}

		// suppression du dernier espace en trop :
		commande = commande.substring(0, commande.length - 1)

		// affichage :
		edittext_commande.setText(commande)
	}

}
