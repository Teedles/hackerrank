/**
 * my solution to the HR acm icpc challenge
 * note, this is a little different to the actual requirements:
 * for example, I generate knowledge of topics randomly and I don't read the number of people and topics from stdin
 */

package acm_icpc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

/**
 * @author Teedles
 *
 */
public class GroupKnowledgeCounter {

	public final static int KNOWLEDGE_BANDWITH = 50;
	public final static int PERSON_BANDWITH = 50;
	
	/**
	 * 
	 */
	public GroupKnowledgeCounter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//SETUP
		int maxGroups = returnMaxGroups(PERSON_BANDWITH);
		BitSet[] people = generatePeople();
		
		long startTime;
		long stopTime;

		List<Group> groups = generateGroups(people);
	
		// COMPUTATION
		startTime = System.currentTimeMillis();
		TopicAndGroupResult maxKnownTopics = countKnownTopicsAndGroups(groups);
		stopTime = System.currentTimeMillis(); 
		
		// PRESENTATION
		System.out.println("Max possible groups is: " + maxGroups);
		System.out.println("Max known topics is: " + maxKnownTopics.getNumbers()[0]);
		System.out.println("Number of Groups that know the max Topics is: " + maxKnownTopics.getNumbers()[1]);
		
		
		// So, its possible that multiple groups know the same number of topics...just different ones
		// How can we deal with this? No idea, however, we *can* enumerate these groups...
		if (maxKnownTopics.getNumbers()[1] > 1) {
			Set<Entry<BitSet, MutableInt>> set = maxKnownTopics.getMaps().entrySet();
			Iterator<Entry<BitSet, MutableInt>> iterator = set.iterator();
	        List<Map.Entry> myList = new ArrayList<>();
			
		      while(iterator.hasNext()) {
		         Map.Entry mentry = iterator.next();
		         System.out.println("Group Knowledge Key: "+ ((BitSet) mentry.getKey()).toString());
		    	 System.out.println("has "+ ((MutableInt) mentry.getValue()).get() + " groups sharing it.");
		    	  
		      }
		}
		
		System.out.println("Computation took: "+ (stopTime - startTime) + " millis");
	}
	
	/**
	 *  for all our groups, determine the max topics known
	 *  also count the amount of groups that know that number of topics
	 * @param groups
	 * @return an int array with the numbers counted
	 */
	private static TopicAndGroupResult countKnownTopicsAndGroups(List<Group> groups) {
		
		int[] maxKnownTopicsAndGroups = new int[] {0,0};
		int maxKnownTopics = 0; 
		Map<BitSet, MutableInt> hmap = new HashMap<BitSet, MutableInt>();

		for (Group g : groups) {		
			if (maxKnownTopics < g.getGroupKnowledge().cardinality()) {
				maxKnownTopics = g.getGroupKnowledge().cardinality();
			}	
		}
	
		maxKnownTopicsAndGroups[0] = maxKnownTopics;
		
		// find out if more than one group knows the maxKnownTopics
		for (Group p : groups) {
			if (p.getGroupKnowledge().cardinality() == maxKnownTopics) {
					maxKnownTopicsAndGroups[1]++;
					
					MutableInt count = hmap.get(p.getGroupKnowledge());
					if (count == null) {
						hmap.put(p.getGroupKnowledge(), new MutableInt());
					} else {
						count.increment();
					}
			}
		}
		
	    TopicAndGroupResult tagr = new TopicAndGroupResult();
	    tagr.setNumbers(maxKnownTopicsAndGroups);
	    tagr.setMaps(hmap);
	      
		return tagr;
	}


	/**
	 *  for our array of people, create the groups they will be a member of
	 *  actually kind of the reverse: every other person will be grouped with the current person
	 *  but we pop the people already grouped off the list to ensure that  p1 and p2 are grouped
	 *  but that p2 will not then form a group with p1. we do this by making a list of the people already grouped up
	 * @param people
	 * @return a list of groups
	 */
	private static List<Group> generateGroups(BitSet[] people) {

		List<Group> tempGroups = new ArrayList<Group>();
		List<BitSet> removePersons = new ArrayList<BitSet>();

		for (BitSet p : people) {

			removePersons.add(p);
			List<BitSet> members = new ArrayList<BitSet>(Arrays.asList(people));

			for (BitSet r : removePersons) {
				members.remove(r);
			}

			for (BitSet q : members) {
				Group g = new Group(p,q);
				tempGroups.add(g);
			}
		}	

		return tempGroups;
	}

	/**
	 * make a bunch of people according to our configured bandwidth
	 * @return an array of people
	 */
	public static BitSet[] generatePeople() {
		Random rand = new Random();
		BitSet[] tempPeople = new BitSet[PERSON_BANDWITH];
		
		for (int i=0; i<PERSON_BANDWITH; i++) {

			BitSet person = new BitSet(KNOWLEDGE_BANDWITH);
			
				for (int k=0; k<KNOWLEDGE_BANDWITH; k++) {
					if (rand.nextBoolean() && rand.nextBoolean()) {
								person.set(k);
					} else {
						continue;
					}
				}
			
				tempPeople[i] = person;
		}
		
		return tempPeople;
	}
	
	/**
	 * we compute the number of possible groups as a check. not really needed.
	 * using N*(N-1)/2 to determine max groups of 2 people 
	 * @param numPeople
	 * @return an int with maxGroups value
	 */
	public static int returnMaxGroups(int numPeople) {
		return numPeople * (numPeople - 1) / 2;	
	}
	
	/**
	 * group class. also overkill really
	 * note: on instantiation, the OR of the two persons knowledge is generated
	 *
	 */
	public static class Group {
		/**
		 * @return the p1
		 */
		public BitSet getP1() {
			return p1;
		}

		/**
		 * @param p1 the p1 to set
		 */
		public void setP1(BitSet p1) {
			this.p1 = p1;
		}

		/**
		 * @return the p2
		 */
		public BitSet getP2() {
			return p2;
		}

		/**
		 * @param p2 the p2 to set
		 */
		public void setP2(BitSet p2) {
			this.p2 = p2;
		}

		/**
		 * @return the groupKnowledge
		 */
		public BitSet getGroupKnowledge() {
			BitSet myOr = (BitSet) getP1().clone();
			myOr.or(getP2());
			return myOr;
		}

		private BitSet p1;
		private BitSet p2;

		public Group(BitSet p1, BitSet p2) {
			this.setP1(p1);
			this.setP2(p2);

		}
	}
	
	
	public static class MutableInt {
		  int value = 1; // note that we start at 1 since we're counting
		  public void increment () { ++value;      }
		  public int  get ()       { return value; }
		}

	// why cant java return two things at once?
	public static class TopicAndGroupResult {
		public int[] getNumbers() {
			return numbers;
		}
		public void setNumbers(int[] numbers) {
			this.numbers = numbers;
		}
		public Map<BitSet, MutableInt> getMaps() {
			return maps;
		}
		public void setMaps(Map<BitSet, MutableInt> maps) {
			this.maps = maps;
		}
		private int[] numbers;
		private Map<BitSet, MutableInt> maps;
	}
}


