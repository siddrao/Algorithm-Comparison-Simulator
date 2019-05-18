package wls;
//
//

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;



import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.graphstream.algorithm.Dijkstra;
import org.graphstream.graph.Edge;
import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.Path;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
public class Simulator implements Initializable {
    public String label;
    public Viewer viewer;
    public Graph graph;
    private Stage viewStage;
    public View view;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public JFrame rightFrame;
    public static Integer count=0;
    public static Integer count2=0;

    public void setStage(Stage stage) {
        viewStage = stage;
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
        graph = exampleGraph();
        graph.addAttribute("ui.quality");
        graph.addAttribute("ui.antialias");
        graph.addAttribute("ui.stylesheet", "url(" + new File("src/wls/test.css").getAbsolutePath() + ")");
        JFrame e = new JFrame();
        e.setDefaultCloseOperation(EXIT_ON_CLOSE);
        viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
        view = viewer.addDefaultView(false);
        viewer.enableXYZfeedback(true);
        e.add((Component) view);
        e.setLocation(200, 0);
        e.setSize((int) screenSize.getWidth() - 600, (int) screenSize.getHeight() - 40);
        e.setVisible(true);
        loadLeftFrame();
        loadRightFrame();
        for (Node n : graph) {
            n.addAttribute("label", n.getId());
        }
        for (Edge ed : graph.getEachEdge()) {
            ed.addAttribute("label", "" + (int) ed.getNumber("length"));
        }

    }

    public Graph exampleGraph() {
        graph = new SingleGraph("example");
        
        
        //////small graph
        graph.addNode("A").addAttribute("xy", 0, 1);
        graph.addNode("B").addAttribute("xy", 1, 2);
        graph.addNode("C").addAttribute("xy", 1, 1);
        graph.addNode("D").addAttribute("xy", 1, 0);
        graph.addNode("E").addAttribute("xy", 2, 2);
        graph.addNode("F").addAttribute("xy", 2, 1);
        graph.addNode("G").addAttribute("xy", 2, 0);
        graph.addEdge("AB", "A", "B").addAttribute("length", 14.0);
        graph.addEdge("AC", "A", "C").addAttribute("length", 9.0);
        graph.addEdge("AD", "A", "D").addAttribute("length", 7.0);
        graph.addEdge("BC", "B", "C").addAttribute("length", 2.0);
        graph.addEdge("CD", "C", "D").addAttribute("length", 10.0);
        graph.addEdge("BE", "B", "E").addAttribute("length", 9.0);
        graph.addEdge("CF", "C", "F").addAttribute("length", 11.0);
        graph.addEdge("DF", "D", "F").addAttribute("length", 15.0);
        graph.addEdge("EF", "E", "F").addAttribute("length", 6.0);
        
        //////large graph
//        graph.addNode("A").addAttribute("xy", 0, 1);
//        graph.addNode("B").addAttribute("xy", 1, 2);
//        graph.addNode("C").addAttribute("xy", 1, 1);
//        graph.addNode("D").addAttribute("xy", 1, 0);
//        graph.addNode("E").addAttribute("xy", 2, 2);
//        graph.addNode("F").addAttribute("xy", 2, 1);
//        graph.addNode("G").addAttribute("xy", 2, 0);
//        graph.addNode("H").addAttribute("xy", 0, 3);
//        graph.addNode("I").addAttribute("xy", 3, 2);
//        graph.addNode("J").addAttribute("xy", 3, 3);
//        graph.addNode("K").addAttribute("xy", 3, 1);
//        graph.addNode("L").addAttribute("xy", 3, 4);
//        graph.addNode("M").addAttribute("xy", 4, 1);
//        graph.addNode("N").addAttribute("xy", 4, 2);
//        graph.addNode("A2").addAttribute("xy", 0.5, 1);
//        graph.addNode("B2").addAttribute("xy", 1.5, 2);
//        graph.addNode("C2").addAttribute("xy", 1.5, 1);
//        graph.addNode("D2").addAttribute("xy", 1.5, 0);
//        graph.addNode("E2").addAttribute("xy", 2.5, 2);
//        graph.addNode("F2").addAttribute("xy", 2.5, 1);
//        graph.addNode("G2").addAttribute("xy", 2.5, 0);
//        graph.addNode("H2").addAttribute("xy", 0.5, 3);
//        graph.addNode("I2").addAttribute("xy", 3.5, 2);
//        graph.addNode("J2").addAttribute("xy", 3.5, 3);
//        graph.addNode("K2").addAttribute("xy", 3.5, 1);
//        graph.addNode("L2").addAttribute("xy", 3.5, 4);
//        graph.addNode("M2").addAttribute("xy", 4.5, 1);
//        graph.addNode("N2").addAttribute("xy", 4.5, 2);
//        graph.addNode("A3").addAttribute("xy", 0, 1.5);
//        graph.addNode("B3").addAttribute("xy", 1, 2.5);
//        graph.addNode("C3").addAttribute("xy", 1, 1.5);
//        graph.addNode("D3").addAttribute("xy", 1, 0.5);
//        graph.addNode("E3").addAttribute("xy", 2, 2.5);
//        graph.addNode("F3").addAttribute("xy", 2, 1.5);
//        graph.addNode("G3").addAttribute("xy", 2, 0.5);
//        graph.addNode("H3").addAttribute("xy", 0, 3.5);
//        graph.addNode("I3").addAttribute("xy", 3, 2.5);
//        graph.addNode("J3").addAttribute("xy", 3, 3.5);
//        graph.addNode("K3").addAttribute("xy", 3, 1.5);
//        graph.addNode("L3").addAttribute("xy", 3, 4.5);
//        graph.addNode("M3").addAttribute("xy", 4, 1.5);
//        graph.addNode("N3").addAttribute("xy", 4, 2.5);
//        graph.addEdge("AB", "A", "B").addAttribute("length", 14.0);
//        //graph.delEdge("AB");
//        graph.addEdge("AC", "A", "C").addAttribute("length", 9.0);
//        graph.addEdge("AD", "A", "D").addAttribute("length", 7.0);
//        graph.addEdge("BC", "B", "C").addAttribute("length", 2.0);
//        graph.addEdge("CD", "C", "D").addAttribute("length", 10.0);
//        graph.addEdge("BE", "B", "E").addAttribute("length", 9.0);
//        graph.addEdge("CF", "C", "F").addAttribute("length", 11.0);
//        graph.addEdge("DF", "D", "F").addAttribute("length", 15.0);
//        graph.addEdge("FE", "F", "E").addAttribute("length", 6.0);
//        graph.addEdge("AH", "A", "H").addAttribute("length", 10.0);
//        graph.addEdge("IJ", "I", "J").addAttribute("length", 17.0);
//        graph.addEdge("EI", "E", "I").addAttribute("length", 12.0);
//        graph.addEdge("FK", "F", "K").addAttribute("length", 11.0);
//        graph.addEdge("IN", "I", "N").addAttribute("length", 1.0);
//        graph.addEdge("CL", "C", "L").addAttribute("length", 11.0);
//        graph.addEdge("NM", "N", "M").addAttribute("length", 5.0);
//        graph.addEdge("EM", "E", "M").addAttribute("length", 2.0);
//        graph.addEdge("A2B2", "A2", "B2").addAttribute("length", 14.0);
//        //graph.delEdge("AB");
//        graph.addEdge("A2C3", "A2", "C3").addAttribute("length", 19.0);
//        graph.addEdge("AD3", "A", "D3").addAttribute("length", 27.0);
//        graph.addEdge("B2C2", "B2", "C2").addAttribute("length", 32.0);
//        graph.addEdge("C3D", "C3", "D").addAttribute("length", 410.0);
//        graph.addEdge("B3E3", "B3", "E3").addAttribute("length", 59.0);
//        graph.addEdge("C2F", "C2", "F").addAttribute("length", 611.0);
//        graph.addEdge("D3F2", "D3", "F2").addAttribute("length", 715.0);
//        graph.addEdge("F2E3", "F2", "E3").addAttribute("length", 86.0);
//        graph.addEdge("A2H2", "A2", "H2").addAttribute("length", 910.0);
//        graph.addEdge("I3J", "I3", "J").addAttribute("length", 117.0);
//        graph.addEdge("E2I3", "E2", "I3").addAttribute("length", 212.0);
//        graph.addEdge("F2K3", "F2", "K3").addAttribute("length", 311.0);
//        graph.addEdge("IN3", "I", "N3").addAttribute("length", 41.0);
//        graph.addEdge("CL2", "C", "L2").addAttribute("length", 511.0);
//        graph.addEdge("N3M3", "N3", "M3").addAttribute("length", 65.0);
//        graph.addEdge("E3M2", "E3", "M2").addAttribute("length", 72.0);
//        graph.addEdge("A3B3", "A3", "B3").addAttribute("length", 814.0);
//        //graph.delEdge("AB");
//        graph.addEdge("A3C", "A3", "C").addAttribute("length", 39.0);
//        graph.addEdge("A3D", "A3", "D").addAttribute("length", 347.0);
//        graph.addEdge("B2C", "B2", "C").addAttribute("length", 2.0);
//        graph.addEdge("CD3", "C", "D3").addAttribute("length", 120.0);
//        graph.addEdge("BE2", "B", "E2").addAttribute("length", 96.0);
//        graph.addEdge("CF3", "C", "F3").addAttribute("length", 161.0);
//        graph.addEdge("D2F", "D2", "F").addAttribute("length", 175.0);
//        graph.addEdge("FE2", "F", "E2").addAttribute("length", 69.0);
//        graph.addEdge("A3H", "A3", "H").addAttribute("length", 130.0);
//        graph.addEdge("I2J2", "I2", "J2").addAttribute("length", 17.0);
//        graph.addEdge("E3I2", "E3", "I2").addAttribute("length", 212.0);
//        graph.addEdge("FK3", "F", "K3").addAttribute("length", 116.0);
//        graph.addEdge("I3N2", "I3", "N2").addAttribute("length", 122.0);
//        graph.addEdge("C2L3", "C2", "L3").addAttribute("length", 11.0);
//        graph.addEdge("NM2", "N", "M2").addAttribute("length", 5.0);
//        graph.addEdge("E3M", "E3", "M").addAttribute("length", 2.0);

        for (Node n : graph) {
            n.addAttribute("ui.label", n.getId());
        }
        for (Edge e : graph.getEachEdge()) {
            e.addAttribute("ui.label", "" + (int) e.getNumber("length"));
        }
        return graph;
    }

    public void loadRightFrame() {
        rightFrame = new JFrame();
        rightFrame.setLayout(new BorderLayout());
        rightFrame.setLocation((int) screenSize.getWidth() - 400, 0);
        rightFrame.setSize(400, (int) screenSize.getHeight() - 40);
        rightFrame.setTitle("Analysis");
        rightFrame.setVisible(true);
    }

    double[] values = new double[5];

    public void updateTable(Dijkstra2 d, String dest) {
        Object[] columns = new String[]{"Source", "Cost", "No of hops", "Time", "Destination"};
        Object[][] data = new Object[][]{};
        DefaultTableModel tm = new DefaultTableModel(data, columns);
        JTable table = new JTable(data, columns);
        table.setModel(tm);
        DecimalFormat df = new DecimalFormat("0.00");
        int hops = 0;
        Node node = graph.getNode(dest);
        for (Edge edge : d.getPathEdges(node)) {
            hops += 1;
        }
        values[0] = Double.parseDouble(df.format(d.getPathLength(node) / 7));            
        values[1]=count2;
        Object[] tmp = {d.getSource().toString(), Double.toString(d.getPathLength(node)), hops, df.format(d.getPathLength(node) / 7), node.toString()};
        tm.addRow(tmp);
       rightFrame.add(new JScrollPane(table), BorderLayout.NORTH);
        rightFrame.setVisible(true);
    }

    public void updateTable3(Dijkstra d) {
        Object[] columns = new String[]{
            "Source", "Cost", "No of hops", "Time", "Destination"
        };

        Object[][] data = new Object[][]{};
        DefaultTableModel tm = new DefaultTableModel(data, columns);
        JTable table3 = new JTable(data, columns);
        table3.setModel(tm);

        for (Node node : graph) {
            DecimalFormat df = new DecimalFormat("0.00");
            int hops = 0;
            for (Edge edge : d.getPathEdges(node)) {
                hops += 1;
            }
            Object[] tmp = {d.getSource().toString(), Double.toString(d.getPathLength(node)), Integer.toString(hops), df.format(d.getPathLength(node) / 7), node.toString()};
            tm.addRow(tmp);
        }
        rightFrame.add(new JScrollPane(table3), BorderLayout.SOUTH);
        rightFrame.setVisible(true);
    }

    public void loadLeftFrame() {
        JFrame sideFrame = new JFrame();
        sideFrame.setLayout(new BorderLayout());
        sideFrame.setLocation(0, 0);
        sideFrame.setSize(200, (int) screenSize.getHeight() - 40);
        sideFrame.setTitle("Options");
        JRadioButton dijkstra = new JRadioButton("Dijkstra");
        JRadioButton bell = new JRadioButton("BellmanFord");
        dijkstra.setSelected(true);
        sideFrame.add(dijkstra);
        sideFrame.add(bell);
        JTextField nodeLabel = new JTextField();
        JButton addNodeBtn = new JButton("Add Node");
        JTextField remLabel = new JTextField();
        JButton remNodeBtn = new JButton("Remove Node");
        
        JButton clr = new JButton("Clear Graph");
        JButton edgeRemove = new JButton("Remove Edge");
        JButton edgeSubmit = new JButton("Add Edge");
        JTextField node1 = new javax.swing.JTextField();
        JLabel node1Lbl = new javax.swing.JLabel("Node 1");
        JTextField node2 = new javax.swing.JTextField();
        JLabel node2Lbl = new javax.swing.JLabel("Node 2");
        JLabel lengthLbl = new JLabel("Cost");
        JTextField lengthText = new JTextField();

        JButton shortestPathSubmit = new JButton("Get Shortest Path");
        JTextField src = new javax.swing.JTextField();
        JLabel srcLbl = new javax.swing.JLabel("Source");
        JTextField dest = new javax.swing.JTextField();
        JLabel destLbl = new javax.swing.JLabel("Dest.");
        JLabel rtable = new JLabel("Enter node");
        JTextField node = new JTextField();
        JButton but = new JButton("Get Routing Table");
        JButton ans = new JButton("Analysis");
        JButton tut = new JButton("TUTORIAL");
       
        JLabel blank = new JLabel();
        dijkstra.setBounds(20, 8, 100, 30);
        bell.setBounds(20,30,120,30);
        nodeLabel.setBounds(30, 65, 120, 30);
        addNodeBtn.setBounds(30,105, 120, 30);
        remLabel.setBounds(30, 150, 120, 30);
        remNodeBtn.setBounds(30, 190, 120, 30);
        clr.setBounds(30,238,120,20);

        node1Lbl.setBounds(30, 260, 120, 30);
        node1.setBounds(30, 290, 120, 30);
        node2Lbl.setBounds(30, 320, 120, 30);
        node2.setBounds(30, 350, 120, 30);
        lengthLbl.setBounds(30, 380, 120, 30);
        lengthText.setBounds(30, 410, 120, 30);
        edgeSubmit.setBounds(30, 440, 120, 30);
        edgeRemove.setBounds(30, 480, 120, 30);
        srcLbl.setBounds(30, 520, 120, 30);
        src.setBounds(80, 520, 80, 30);
        destLbl.setBounds(35, 560, 120, 30);
        dest.setBounds(80, 560, 80, 30);
        shortestPathSubmit.setBounds(15, 600, 150, 30);
        rtable.setBounds(30, 635, 150, 30);
        node.setBounds(30, 660, 100, 30);
        but.setBounds(15, 700, 140, 40);
        ans.setBounds(15, 745, 140, 40);
        tut.setBounds(15, 795, 140, 40);
        sideFrame.add(nodeLabel);
        sideFrame.add(addNodeBtn);
        
         sideFrame.add(remLabel);
        sideFrame.add(remNodeBtn);
        sideFrame.add(clr);
        

        sideFrame.add(node1Lbl);
        sideFrame.add(node1);
        sideFrame.add(node2Lbl);
        sideFrame.add(node2);
        sideFrame.add(lengthLbl);
        sideFrame.add(lengthText);
        sideFrame.add(edgeSubmit);
        sideFrame.add(edgeRemove);

        sideFrame.add(src);
        sideFrame.add(srcLbl);
        sideFrame.add(dest);
        sideFrame.add(destLbl);
        //sideFrame.add(nsp);
        sideFrame.add(shortestPathSubmit);
        sideFrame.add(rtable);
        sideFrame.add(node);
        
        sideFrame.add(but);
        sideFrame.add(ans);
        sideFrame.add(tut);
        sideFrame.add(blank);
        sideFrame.setVisible(true);

        dijkstra.addActionListener((ActionEvent e) -> {
            if (dijkstra.isSelected()) {
                bell.setSelected(false);        
            }
        });
        bell.addActionListener((ActionEvent e) -> {
            if (bell.isSelected()) {
                dijkstra.setSelected(false);
            }
        });

        addNodeBtn.addActionListener((ActionEvent e) -> {
            addNode(nodeLabel.getText().toUpperCase());
        });
                
        remNodeBtn.addActionListener((ActionEvent e) -> {
            remNode(remLabel.getText().toUpperCase());
        });

        edgeSubmit.addActionListener((ActionEvent e) -> {
            addEdge(node1.getText().toUpperCase(), node2.getText().toUpperCase(), Integer.parseInt(lengthText.getText()));
        });
        edgeRemove.addActionListener((ActionEvent e) -> {           
            remEdge(node1.getText().toUpperCase(),node2.getText().toUpperCase());           
        });


        but.addActionListener((ActionEvent e) -> {
            calculateDijkstraTable(node.getText().toUpperCase(), dest.getText().toUpperCase());
            
        });
        
        
        clr.addActionListener((ActionEvent e) -> {
            values=null;
            int k=0;
        while(k<6)
             for (Node nod : graph.getEachNode())
                graph.removeNode(nod);
            k++;
        });
        
        shortestPathSubmit.addActionListener((ActionEvent e) -> {
            Dijkstra2 dijkstraObj = null;
            if (bell.isSelected()) {
                calculateBell(src.getText().toUpperCase(),dest.getText().toUpperCase());
                System.out.println("BELLLLLLL"+count);
                              
            } 
            else 
            {
              dijkstraObj=calculateDijkstra(src.getText().toUpperCase(), dest.getText().toUpperCase());
                System.out.println("DIIIIJJJJJ"+count2);
                updateTable(dijkstraObj, dest.getText().toUpperCase());   
            }           
        });

        ans.addActionListener((ActionEvent e) -> {
            JFrame f = new JFrame();
            f.setSize((int) screenSize.getWidth()-200,(int) screenSize.getHeight()-200);
            String[] names = new String[6];
            names[0] = "Dijkstra(Speed of Convergence,Msg Complexity )";

            names[1] = "";

            names[2] = "BellmanFord(Speed of Convergence,Msg Complexity)";

            names[3] = "";

            names[4] = "";

            names[5] = "";
            f.getContentPane().add(new TableExample(values, names, "SIMULATION ANALYSIS"));          
            f.setVisible(true);
        });

    
        tut.addActionListener((ActionEvent e) -> {
           Browser browser = new Browser();
           BrowserView browserView = new BrowserView(browser);
           JFrame frame = new JFrame("Tutorial");
           JPanel panel = new JPanel(new BorderLayout());
           panel.add(browserView,BorderLayout.CENTER);
           frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
           frame.add(panel, BorderLayout.CENTER);
           frame.setLocationRelativeTo(null);
           frame.setSize(800, 500);
//           https://jxbrowser.support.teamdev.com/support/solutions/articles/9000013104-loading-html                
           browser.addLoadListener(new LoadAdapter() {
            @Override
            public void onFinishLoadingFrame(FinishLoadingEvent event) {
                if (event.isMainFrame()) {
                    System.out.println("HTML is loaded.");
                }
            }
        });
            browser.loadURL(Simulator.class.getResource("intro.html").toString());
            frame.setVisible(true);
            frame.validate();
        });

    }

    private void addNode(String label) {
        String[] nodes = label.split(" ");
        for (String node : nodes) {
            Node newNode = graph.addNode(node);
            newNode.addAttribute("xy", ThreadLocalRandom.current().nextInt(3, 4 + 1), ThreadLocalRandom.current().nextInt(3, 4 + 1));
            newNode.addAttribute("ui.label", newNode.getId());
        }
    }
    
    private void remNode(String label) {
         graph.removeNode(label);
        }
    private void remEdge(String label,String label2) {
         graph.removeEdge(label,label2);
    }
    private void addEdge(String node1, String node2, double length) {
        Edge e = graph.addEdge(node1 + node2, node1, node2);
        e.setAttribute("length", length);
        e.setAttribute("layout.weight", length);
        e.setAttribute("ui.label", "" + (int) e.getNumber("length"));
    }

    private void resetEdgeColors() {
        graph.getEdgeSet().stream().forEach((edge) -> {
            edge.setAttribute("ui.style", "fill-color:black;");
        });
        graph.getNodeSet().stream().forEach((node) -> {
            node.setAttribute("ui.style", "fill-color:black;");
        });
    }
//http://graphstream-project.org/doc/Algorithms/Shortest-path/Dijkstra/
    private Dijkstra2 calculateDijkstra(String srcStr, String destStr) {

        resetEdgeColors();
        // Edge lengths are stored in an attribute called "length"
        // The length of a path is the sum of the lengths of its edges
        Dijkstra2 dijkstra2 = new Dijkstra2(Dijkstra2.Element.EDGE, null, "length");

        // Compute the shortest paths in g from A to all nodes
        dijkstra2.init(graph);
        dijkstra2.setSource(graph.getNode(srcStr));
        dijkstra2.compute();
        

        // Color in blue all the nodes on the shortest path form A to B
        for (Node node : dijkstra2.getPathNodes(graph.getNode(destStr))) {
            node.addAttribute("ui.style", "fill-color: blue;");
        }

        // Print the shortest path from A to B
        for (Node node : graph) {
            System.out.printf("%s->%s:%10.2f%n", dijkstra2.getSource(), node,
                    dijkstra2.getPathLength(node));
        }
        System.out.println("Dijkstra: " + srcStr + " -> " + destStr + " \n"
                + "Path: " + dijkstra2.getPath(graph.getNode(destStr)) + "\n"
                + "Cost: " + dijkstra2.getPathLength(graph.getNode(destStr)));

        // Build a list containing the nodes in the shortest path from A to B
        // Note that nodes are added at the beginning of the list
        // because the iterator traverses them in reverse order, from B to A
        List<Node> list1 = new ArrayList<>();
        for (Node node : dijkstra2.getPathNodes(graph.getNode(destStr))) {
            list1.add(0, node);
        }

        for (int i = 0; i < list1.size() - 1; i++) {
            Edge e = list1.get(i).getEdgeBetween(list1.get(i + 1));
            e.setAttribute("ui.style", "fill-color: red;");
        }
        return dijkstra2;
    }

    private void calculateDijkstraTable(String srcStr, String destStr) {

        //resetEdgeColors();
        // Edge lengths are stored in an attribute called "length"
        // The length of a path is the sum of the lengths of its edges
        Dijkstra dijkstra = new Dijkstra(Dijkstra.Element.EDGE, null, "length");

        // Compute the shortest paths in g from A to all nodes
        dijkstra.init(graph);
       dijkstra.setSource(graph.getNode(srcStr));
        dijkstra.compute();

        for (Node node : graph) {
            System.out.printf("%s->%s:%10.2f%n", dijkstra.getSource(), node,
                    dijkstra.getPathLength(node));
        }
        System.out.println("Dijkstra: " + srcStr + " -> " + destStr + " \n"
                + "Path: " + dijkstra.getPath(graph.getNode(destStr)) + "\n"
                + "Cost: " + dijkstra.getPathLength(graph.getNode(destStr)));

        // Build a list containing the nodes in the shortest path from A to B
        // Note that nodes are added at the beginning of the list
        // because the iterator traverses them in reverse order, from B to A
        List<Node> list1 = new ArrayList<>();
        for (Node node : dijkstra.getPathNodes(graph.getNode(destStr))) {
            list1.add(0, node);
        }

        for (int i = 0; i < list1.size() - 1; i++) {
            Edge e = list1.get(i).getEdgeBetween(list1.get(i + 1));
            // e.setAttribute("ui.style", "fill-color: red;");
        }
        updateTable3(dijkstra);
        // cleanup to save memory if solutions are no longer needed
        //dijkstra.clear();
    }

    private void calculateBell(String srcStr, String destStr) 
    {
         resetEdgeColors();
         
         BellmanFord2 bf = new BellmanFord2("length",srcStr);
         bf.init(graph);
         bf.compute();

           Path path = bf.getShortestPath(graph.getNode(destStr));     
           path.getNodeSet().stream().forEach((node) -> {
               node.addAttribute("ui.style", "fill-color: blue;");
        });
             
              // Print the shortest path from A to B
        System.out.println("Bellman Ford: " + srcStr + " -> " + destStr + " \n"
                + "Path: " + bf.getShortestPath(graph.getNode(destStr)));
             
        List<Node> list1 = new ArrayList<>();
        path.getNodeSet().stream().forEach((node) -> {
            list1.add(0, node);
        });
        //System.out.print(list1);
        int costbf=0;
        
         for (int i = 0; i < list1.size() - 1; i++) {
            Edge e = list1.get(i).getEdgeBetween(list1.get(i + 1));
            e.setAttribute("ui.style", "fill-color: red;");
            costbf += e.getNumber("length");
        }
        int hopsbel = list1.size();
        System.out.println("Cost: " + costbf);
        if(hopsbel==0)
             JOptionPane.showMessageDialog(null, "Undirected Graph Detected", "Warning", JOptionPane.PLAIN_MESSAGE);
            else
        bTable(bf, srcStr, destStr, costbf, hopsbel,count);
        
        }
    
     public void bTable(BellmanFord2 bf, String src, String dest, double cost, int hopsbel,int count) {
        Object[] columns = new String[]{"Source", "Cost", "No of hops", "Time", "Destination"};
        Object[][] data = new Object[][]{};
        DefaultTableModel tm = new DefaultTableModel(data, columns);
        JTable table2 = new JTable(data, columns);
        table2.setModel(tm);
        Node node = graph.getNode(dest);
        JLabel label = new JLabel("Bellman Ford");
        DecimalFormat df = new DecimalFormat("0.00");
        //bf.setTarget(node.toString());
       Path path = bf.getShortestPath(graph.getNode(dest)); 
        //int hops=0;
       System.out.print(path);
        List<Node> list1 = new ArrayList<>();
        String time = df.format(cost / 7);
        double t1 = Double.parseDouble(time);
        //values[3] = 0;
        values[2] = t1;
        values[3] = count;
        Object[] tmp = {src, Double.toString(cost), hopsbel, time, dest};
        tm.addRow(tmp);

        rightFrame.add(new JScrollPane(table2), BorderLayout.CENTER);
        rightFrame.setVisible(true);
    }
     
    }

