fun main() {
    val rentalOffice = RentalOffice()
    rentalOffice.rentForCustomer()
}

class RentalOffice {

    fun rentForCustomer() {
        val rental1 = Rental(Car(name = "Mustang", type = CarType.MUSCLE), 5)
        val rental2 = Rental(Car(name = "Lambo", type = CarType.SUPERCAR), 20)
        val customer = Customer("Liviu")
        customer.addRental(rental1)
        customer.addRental(rental2)

        print(customer.billingStatement())
    }
}

class Customer(val name: String) {
    private val _rentals = ArrayList<Rental>()
    private var frequentRenterPoints: Int = 0

    fun addRental(arg: Rental) {
        _rentals.add(arg)
    }

    fun billingStatement(): String {

        var totalAmount = 0.0

        var result = "Rental Record for $name\n"

        _rentals.forEach { rental ->
            //determine amounts for each rental
            val thisAmount = rental.calculateAmount()

            // add frequent renter points
            frequentRenterPoints += 1
            // add bonus for a two day new release rental
            if (rental.isEligibleForPoints()) {
                frequentRenterPoints += 1
            }

            //show figures for this rental
            result += "\t" + rental.getCarName() + "\t" + thisAmount.toString() + "\n";
            totalAmount += thisAmount;
        }
        //add footer lines
        result += "Final rental payment owed $totalAmount\n"
        result += "You received an additional $frequentRenterPoints frequent customer points"
        return result
    }
}

class Rental(private val car: Car, private val daysRented: Int) {

    fun calculateAmount(): Double {
        return car.type.run {
            var totalAmount = initalCost
            if (daysRented > daysWithInitialCost) {
                totalAmount += (daysRented - daysWithInitialCost).toDouble() * costPerExtraDay
            }
            totalAmount
        }
    }

    fun isEligibleForPoints() = (car.type == CarType.SUPERCAR) && daysRented > 1

    fun getCarName() = car.name
}

class Car(val type: CarType, val name: String)

enum class CarType(val initalCost: Double, val costPerExtraDay: Double, val daysWithInitialCost: Int) {
     MUSCLE(200.0, 50.0, 3),
     ECONOMY(80.0, 30.0, 2),
     SUPERCAR(0.0, 200.0, 0)
}

