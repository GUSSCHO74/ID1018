import java.util.*; // Scanner, Locale
import static java.lang.System.out;

class Temperatures1
{
	public static void main (String[] args){
 		out.println("TEMPERATURES\n");

		// input tool
		Scanner in = new Scanner(System.in);
		in.useLocale(Locale.US);

        // enter the number of weeks and measurements
		out.print("number of weeks: ");
		int nofWeeks = in.nextInt();
		out.print("number of measurements per week: ");
		int nofMeasurementsPerWeek = in.nextInt();

        // storage space for temperature data
        double[][] t = new double[nofWeeks + 1][nofMeasurementsPerWeek + 1];

        // read the temperatures
		for (int week = 1; week <= nofWeeks; week++)
		{
			out.println("temperatures - week " + week + ":");
			for (int measurementIndex = 1; measurementIndex <= nofMeasurementsPerWeek; measurementIndex++)
				t[week][measurementIndex] = in.nextDouble();
		}
		out.println("");

		// show the temperatures
		out.println("the temperatures");
        for (int week = 1; week <= nofWeeks; week++)
        {
			for (int measurement = 1; measurement <= nofMeasurementsPerWeek; measurement++)
			    out.print(t[week][measurement] + " ");
		    out.println("");
		}
		out.println("");

		// the least, greatest and average temperatures - weekly
		double[] minT = new double[nofWeeks + 1];
		double[] maxT = new double[nofWeeks + 1];
		double[] sumT = new double[nofWeeks + 1];
		double[] avgT = new double[nofWeeks + 1];
		// add code here
		for (int week = 1; week <= nofWeeks; week++){
			minT[week] = t[week][1];
			maxT[week] = t[week][1];
			for(int measurementIndex = 1; measurementIndex <= nofMeasurementsPerWeek; measurementIndex++){
				double current = t[week][measurementIndex];

				if(current < minT[week])
					minT[week] = current;
				if(current > maxT[week])
					maxT[week] = current;

				sumT[week] += current;
			}
			avgT[week] = sumT[week] / nofMeasurementsPerWeek;
		}
		
		// show the least, greatest and average temperatures
		// värdena skrivs ut veckovis i kolumner
		out.println("the least, greatest and average temperatures" + " - weekly");
		for (int week = 1; week <= nofWeeks; week++)
			out.print(minT[week] + " ");
		out.println("");
		for (int week = 1; week <= nofWeeks; week++)
			out.print(maxT[week] + " ");
		out.println("");
		for (int week = 1; week <= nofWeeks; week++)
			out.print(avgT[week] + " ");
		out.println("");
		out.println();

		// the least, greatest and average temperatures - whole period
		double minTemp = minT[1];
		double maxTemp = maxT[1];
		double sumTemp = sumT[1];
		double avgTemp = 0;
		// add code here
		for (int week = 2; week <= nofWeeks; week++){
			if(minT[week] < minTemp){
				minTemp = minT[week];
			}
			if(maxT[week] > maxTemp){
				maxTemp = maxT[week];
			}

			sumTemp += sumT[week];	
		}
		avgTemp = sumTemp / (nofWeeks * nofMeasurementsPerWeek);

        // show the least, greatest and average temperature for
        // the whole period
		out.println("the least, greatest and average temperature" + " - whole period");
        out.println(minTemp + "\n" + maxTemp + "\n" + avgTemp);
    }
}
