public class Question_C {

	/**
	 * Should speak for itself.
	 */
	public static class Edge {
		public int leftNode;
		public int rightNode;
		public int weight;
		public Edge(int left, int right, int weight) {
			this.leftNode = left;
			this.rightNode = right;
			this.weight = weight;
		}
	}
	
	/**
	 * A city is defined by:
	 * 
	 * 1. An array of node each represented by an integer
	 * 2. An array of weighted edge connecting two nodes in city 
	 */
	public static class City {
		public int[] nodes;
		public Edge[] edges;
		public City(int[] nodes, Edge[] edges) {
			this.nodes = nodes;
			this.edges = edges;
		}
	}
	
	/**
	 * The required function:
	 * 
	 * 0. If camera array is empty i.e. no node can be covered, return false;
	 * 1. For each city.nodes N:
	 *    1.1. Check if N is member of camera array, if true, go to next N
	 *    1.2. For each city.edges E:
	 *         1.2.1 If E.leftNode is member of camera array && E.rightNode == N && E.weight <= 25, go to next edge
	 *         1.2.2 If E.rightNode is member of camera array && E.leftNode == N && E.weight <= 25, go to next edge
	 *    1.3 Return false, as N is not covered
	 * 2. Return true
	 * 
	 * 
	 * Complexity of this function:
	 * 
	 * In step 1, each node is iterated. This makes the algorithm at least O(n).
	 * 
	 * Inside of this loop, the most expensive operation is not array membership
	 * checking, which is another O(n) operation, but the potentially large
	 * number of edge to iterate through.
	 * 
	 * By simple graph theory, max number of edge (i.e. nodes are maximally
	 * connected to one and other) = n(n-1)/2 which is asymptotically O(n^2).
	 * 
	 * Therefore time complexity of this algorithm is O(n^3) and is polynomial.
	 */
	public static boolean cover(City city, int[] camera) {
		
		// 0.
		if (camera == null || camera.length <= 0) return false;
		
		// 1.
		for (int N: city.nodes) {
			
			// 1.1
			if (isArrayContainElement(camera, N)) continue;
			
			// 1.2
			boolean isNextToNodeCoveredByCamera = false;
			for (Edge E: city.edges) {
				
				// 1.2.1
				if (isArrayContainElement(camera, E.leftNode) && E.rightNode == N && E.weight <= 25) {
					isNextToNodeCoveredByCamera = true;
					break;
				}
				
				// 1.2.2
				if (isArrayContainElement(camera, E.rightNode) && E.leftNode == N && E.weight <= 25) {
					isNextToNodeCoveredByCamera = true;
					break;
				}
			}
			
			// 1.3
			return isNextToNodeCoveredByCamera;
		}
		
		// 2
		return true;
	}
	
	/**
	 * Utility. Avoid using Java SDK's Arrays.binarySearch which certainly
	 * performs better than O(n).
	 */
	public static boolean isArrayContainElement(int[] array, int element) {
		if (array == null) return false;
		for (int a : array) {
			if (a == element) return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws Exception {
		
		testCase_1();
		testCase_2();
		testCase_3();
		testCase_4();
		testCase_5();
		System.out.println("Finished without error");
	}
	
	/**
	 * Test case 1
	 * Given: Single node city, no camera
	 * Expected outcome: cover() should return false
	 */
	public static void testCase_1() throws Exception {
		
		int[] node_1 = new int[] { 0xA };
		Edge[] edge_1 = new Edge[] {};
		City city_1 = new City(node_1, edge_1);
		int[] camera_1 = new int[] {};
		
		if (cover(city_1, camera_1)) {
			throw new Exception("Case 1 failed");
		}
		else {
			System.out.println("Case 1 passed");
		}
	}
	
	/**
	 * Test case 2
	 * Given: Single node city, single camera
	 * Expected outcome: cover() should return true
	 */
	public static void testCase_2() throws Exception {
		
		int[] node_2 = new int[] { 0xA };
		Edge[] edge_2 = new Edge[] {};
		City city_2 = new City(node_2, edge_2);
		int[] camera_2 = new int[] { 0xA };
		
		if (cover(city_2, camera_2)) {
			System.out.println("Case 2 passed");
		}
		else {
			throw new Exception("Case 2 failed");
		}
	}
	
	/**
	 * Test case 3
	 * Given: City of two node: A-B, camera at A but edge has weight > 25, thus
	 *        B is not covered
	 * Expected outcome: cover() should return false
	 */
	public static void testCase_3() throws Exception {
		
		int[] node_3 = new int[] { 0xA, 0xB };
		Edge[] edge_3 = new Edge[] {
				new Edge(0xA, 0xB, 26)
		};
		City city_3 = new City(node_3, edge_3);
		int[] camera_3 = new int[] { 0xA };
		
		if (cover(city_3, camera_3)) {
			throw new Exception("Case 3 failed");
		}
		else {
			System.out.println("Case 3 passed");
		}
	}
	
	/**
	 * Test case 4
	 * Given: City of two node: A-B, camera at A but edge has weight = 25, thus
	 *        A and B are both covered
	 * Expected outcome: cover() should return true
	 */
	public static void testCase_4() throws Exception {
		
		int[] node_4 = new int[] { 0xA, 0xB };
		Edge[] edge_4 = new Edge[] {
				new Edge(0xA, 0xB, 25)
		};
		City city_4 = new City(node_4, edge_4);
		int[] camera_4 = new int[] { 0xA };
		
		if (cover(city_4, camera_4)) {
			System.out.println("Case 4 passed");
		}
		else {
			throw new Exception("Case 4 failed");
		}
	}
	
	/**
	 * Test case 5
	 * Given: City of three node: A-B-C, camera at A and B but C is connected to
	 *        B with edge >  weight 25, thus C is not covered.
	 * Expected outcome: cover() should return false
	 */
	public static void testCase_5() throws Exception {
		
		int[] node_5 = new int[] { 0xA, 0xB, 0xC };
		Edge[] edge_5 = new Edge[] {
				new Edge(0xA, 0xB, 1),
				new Edge(0xB, 0xC, 30)
		};
		City city_5 = new City(node_5, edge_5);
		int[] camera_5 = new int[] { 0xA, 0xB };
		
		if (cover(city_5, camera_5)) {
			throw new Exception("Case 5 failed");
		}
		else {
			System.out.println("Case 5 passed");
		}
	}
}
