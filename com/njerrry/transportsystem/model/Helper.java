package com.njerrry.transportsystem.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.njerrry.transportsystem.graphalgorithm.GraphAlgorithm;

public class Helper {

	public static TransportSystem ReadTransportSystem(String nodesFilename, String edgesFilename)
			throws FileNotFoundException, IOException {

		List<Road> listOfRoads = new ArrayList<>();
		List<Vehicle> listOfVehicles = new ArrayList<>();

		Map<Integer, Coordinates> nodes = new HashMap<>();
		Map<Coordinates, List<Road>> roads = new HashMap<Coordinates, List<Road>>();

		try (BufferedReader br = new BufferedReader(new FileReader(nodesFilename))) {
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				String[] values = line.split(" ");
				nodes.put(Integer.parseInt(values[0]),
						new Coordinates(Double.parseDouble(values[1]), -Double.parseDouble(values[2])));
			}
		}

		try (BufferedReader br = new BufferedReader(new FileReader(edgesFilename))) {
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				String[] values = line.split(" ");
				Road road = new Road(nodes.get(Integer.parseInt(values[1])), nodes.get(Integer.parseInt(values[2])));
				listOfRoads.add(road);
			}
		}

		try (BufferedReader br = new BufferedReader(new FileReader("NA.cedge.txt"))) {
			for (String line; (line = br.readLine()) != null;) {
				String[] l = line.split(" ");
				int firstNodeId = Integer.parseInt(l[1]);
				int secondNodeId = Integer.parseInt(l[2]);
				Road road = new Road(nodes.get(firstNodeId), nodes.get(secondNodeId));

				// new Vehicle().addRoad(road);

				if (roads.get(nodes.get(firstNodeId)) == null) {
					roads.put(nodes.get(firstNodeId), new ArrayList<Road>());
					roads.get(nodes.get(firstNodeId)).add(road);
				} else {
					roads.get(nodes.get(firstNodeId)).add(road);
				}

				if (roads.get(nodes.get(secondNodeId)) == null) {
					roads.put(nodes.get(secondNodeId), new ArrayList<Road>());
					roads.get(nodes.get(secondNodeId)).add(road);
				} else {
					roads.get(nodes.get(secondNodeId)).add(road);
				}

			}
		}

		try (BufferedReader br = new BufferedReader(new FileReader("NA.cvehicles.txt"))) {
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				String[] values = line.split(" ");

				GraphAlgorithm ga = new GraphAlgorithm(nodes, roads);
				ga.calculateDijkstra(nodes.get(Integer.parseInt(values[2])));
				Vehicle car = new Car(values[0], nodes.get(Integer.parseInt(values[1])),
						ga.shortestPathsToCurrentNode.get(nodes.get(Integer.parseInt(values[3]))));

				listOfVehicles.add(car);
			}
		}

		return new TransportSystem(listOfVehicles, listOfRoads);

	}

}
