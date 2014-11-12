import java.io.*;

class Network {

    Graph graph;

    Network() {
        graph = new Graph();


    }
    boolean load(String filename) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(filename));
            String line;
            int counter = 0;
            while((line = br.readLine()) != null) {
                ++counter;
                String[] all = line.split(",");
                if (all.length != 4) {
                    throw new ParseException(line, counter);
                }
                int buss = Integer.parseInt(all[0].trim());
                String from = all[1].trim();
                String to   = all[2].trim();
                int time    = Integer.parseInt(all[3].trim());

                graph.addVertex(from);
                graph.addVertex(to);
                graph.connect(from, to, time);

                for(String s:all) {
                    System.out.println("\""+s.trim()+"\"");

                }
            }

        } catch(IOException e) {
            System.out.println(e);
        } catch(ParseException e) {
            System.out.println(e);
        }finally {
            try {
                if (br != null)
                    br.close();
            }
            catch(IOException e) {
                System.out.println(e);
            }
        }
        System.out.println(graph);




        graph.search("Biomedicinskt centrum", "Granby Centrum" );
        return true;
    }
}

