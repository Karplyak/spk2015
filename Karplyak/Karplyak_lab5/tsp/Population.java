package ua.ga.tsp;

public class Population {

	// Зберігання популяції турів
	private Tour[] tours = null;

	// �������� ���������
	public Population(int populationSize, boolean initialise, TourManager tourManager) {
		tours = new Tour[populationSize];

		if (initialise) {
			// Створення індивідумів
			for (int i = 0; i < populationSize(); i++) {
				Tour newTour = new Tour(tourManager);
				newTour.generateIndividual();
				saveTour(i, newTour);
			}
		}
	}

	// �������� ������
	public void saveTour(int index, Tour tour) {
		tours[index] = tour;
	}

	// Отримати прохід з популяції
	public Tour getTour(int index) {
		return tours[index];
	}

	// Отримати кращий прохід в популяції
	public Tour getFittest() {
		Tour fittest = tours[0];
		
		// Переглянути індивідуми щоб знайти найкращий
		for (int i = 1; i < populationSize(); i++) {
			if (fittest.getFitness() <= getTour(i).getFitness()) {
				fittest = getTour(i);
			}
		}
		return fittest;
	}

	// Отримати розмір популяції
	public int populationSize() {
		return tours.length;
	}
}
