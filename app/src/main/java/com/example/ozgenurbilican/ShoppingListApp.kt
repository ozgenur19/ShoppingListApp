package com.example.ozgenurbilican

open class Item(val name: String, val price: Double)

class Food(name: String, price: Double, val weight: Double) : Item(name, price)

class Cloth(name: String, price: Double, val type: String) : Item(name, price)

class ShoppingList {
    private val items = mutableListOf<Item>()

    fun addItem(item: Item) {
        items.add(item)
        println("${item.name} added successfully!")
    }

    fun displayItems() {
        println("Your shopping list:")
        if (items.isEmpty()) {
            println("Your shopping list is empty!")
        } else {
            items.forEach { item ->
                when (item) {
                    is Food -> println("${item.name} ${item.price}$ ${item.weight}kg")
                    is Cloth -> println("${item.name} ${item.price}$ ${item.type}")
                }
            }
        }
    }

    fun deleteItem(itemName: String) {
        val item = items.find { it.name == itemName }
        if (item != null) {
            items.remove(item)
            println("$itemName is deleted successfully!")
        } else {
            println("Item not found in the list.")
        }
    }
}

fun main() {
    println("Welcome To Shopping List App")
    val shoppingList = ShoppingList()
    var choiceProcess: Int

    do {
        print("Make your choice (1-2-3-4):\n 1. Add Item\n 2. Display Item\n 3. Delete Item\n 4. Exit\n Your choice is: ")
        val input = readLine()
        choiceProcess = input?.toIntOrNull() ?: 0

        when (choiceProcess) {
            1 -> {
                print("Enter the item type you want to add (1 for Food, 2 for Cloth): ")
                val itemTypeInput = readLine()
                val itemType = itemTypeInput?.toIntOrNull() ?: 0
                if (itemType in 1..2) {
                    print("Please enter the name: ")
                    val name = readLine().toString()
                    print("Please enter the price: ")
                    val priceInput = readLine()
                    val price = priceInput?.toDoubleOrNull() ?: 0.0
                    when (itemType) {
                        1 -> {
                            print("Please enter the weight: ")
                            val weightInput = readLine()
                            val weight = weightInput?.toDoubleOrNull() ?: 0.0
                            shoppingList.addItem(Food(name, price, weight))
                        }
                        2 -> {
                            print("Please enter the type: ")
                            val type = readLine().toString()
                            shoppingList.addItem(Cloth(name, price, type))
                        }
                    }
                } else {
                    println("Invalid number.")
                }
            }
            2 -> shoppingList.displayItems()
            3 -> {
                print("Enter the name of the item you want to delete: ")
                val itemName = readLine().toString()
                shoppingList.deleteItem(itemName)
            }
            4 -> println("Exiting...")
            else -> println("Invalid choice.")
        }
        println("----------------------------------------")
    } while (choiceProcess != 4)
}
