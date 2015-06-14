package ua.ga.tsp;

import java.util.Date;
import java.util.Random;

public class TSP_GA {
	private TourManager tourManager = new TourManager();
	private GA ga = new GA(tourManager);

	public TSP_GA(int x[], int y[], int populationSize) {
		// Створення і додавання міста
		for (int i = 0; i < x.length; i++) {
			tourManager.add(new City(x[i], y[i]));
		}

		// Ініціалізація популяції
		Population pop = new Population(populationSize, true, tourManager);
		System.out.println("Initial distance: " + pop.getFittest().getDistance());
		
		Date currentTimeBefore = new Date();
		long timeBefore = currentTimeBefore.getTime();
		// System.out.println("Time: " + timeBefore);
		// Розвинення населеня на 100 поколінь
		
		pop = ga.evolvePopulation(pop);
		for (int i = 0; i < 100; i++) {///<<<<<<<<<<<Population
			pop = ga.evolvePopulation(pop);
		}
		Date currentTimeAfter = new Date();
		long timeAfter = currentTimeAfter.getTime();
		
		// System.out.println("Time: " + timeAfter);
		long time = timeAfter - timeBefore;

		// Вивід результатів
		System.out.println("Finished");
		System.out.println("Final distance: " + pop.getFittest().getDistance());
		System.out.println("Time: " + time );
		System.out.println("Solution:");
		System.out.println(pop.getFittest());
	}

	public static void main(String[] args) {
		int citySize = 10;
		int[] x = {20, 3, 6, 19, 4, 4, 6, 6, 0, 7};//new int[citySize];
		int[] y = {11, 15, 3, 20, 19, 10, 19, 9, 6, 4};//new int[citySize];
	
		
		/*int citySize = 20;
		int[] x = {12,	17,	16,	0,	9,	6,	17,	2,	5,	12,	7,	4,	17,	9,	11,	16,	1,	0,	5,	3};//new int[citySize];
		int[] y =  {5,	1,	19,	4,	14,	2,	3,	6,	2,	15,	18,	15,	3,	9,	5,	10,	15,	9,	13,	20};//new int[citySize];
		*/
			
		/*int citySize = 50;
		int[] x = {17, 2, 13, 5, 20, 3, 20, 16, 8, 1617, 2, 13, 5, 20, 3, 20, 16, 8, 1617, 2, 13, 5, 20, 3, 20, 16, 8, 1617, 2, 13, 5, 20, 3, 20, 16, 8, 1617, 2, 13, 5, 20, 3, 20, 16, 8, 1617, 2, 13, 5, 20, 3, 20, 16, 8, 16};//new int[citySize];
		int[] y =  {19, 19, 2, 11, 20, 20, 10, 2, 19, 2019, 19, 2, 11, 20, 20, 10, 2, 19, 2019, 19, 2, 11, 20, 20, 10, 2, 19, 2019, 19, 2, 11, 20, 20, 10, 2, 19, 2019, 19, 2, 11, 20, 20, 10, 2, 19, 2019, 19, 2, 11, 20, 20, 10, 2, 19, 20};//new int[citySize];
		*/
		Random random = new Random();
		
		System.out.println("/* ----------------------- */");
		System.out.println("City size: " + citySize);
		System.out.print("[");
		/*
		for (int i = 0; i < citySize; i++) {
			x[i] = random.nextInt(citySize);
			y[i] = random.nextInt(citySize);
			
			System.out.print(x[i] + "," + y[i] + ";");
		}
		*/

		System.out.println("]");

		System.out.println("/* ----------------------- */");
		new TSP_GA(x, y, 20);
		System.out.println("/* ----------------------- */");
		new TSP_GA(x, y, 100);
		System.out.println("/* ----------------------- */");
		new TSP_GA(x, y, 200);
			
	}
}