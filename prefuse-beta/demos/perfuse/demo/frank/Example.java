package perfuse.demo.frank;

import java.io.*;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import prefuse.Constants;
import prefuse.Display;
import prefuse.Visualization;
import prefuse.action.ActionList;
import prefuse.action.RepaintAction;
import prefuse.action.assignment.DataColorAction;
import prefuse.action.assignment.ColorAction;
import prefuse.action.assignment.SizeAction;
import prefuse.action.layout.RandomLayout;
import prefuse.action.layout.graph.ForceDirectedLayout;
import prefuse.activity.Activity;
import prefuse.activity.ActivityAdapter;
import prefuse.activity.ActivityListener;
import prefuse.controls.*;
import prefuse.data.Schema;
import prefuse.data.Graph;
import prefuse.data.io.*;
import prefuse.data.tuple.TupleSet;
import prefuse.render.DefaultRendererFactory;
import prefuse.render.LabelRenderer;
import prefuse.util.ColorLib;
import prefuse.util.PrefuseLib;
import prefuse.util.force.DragForce;
import prefuse.util.force.ForceItem;
import prefuse.util.force.ForceSimulator;
import prefuse.util.force.NBodyForce;
import prefuse.util.force.SpringForce;
import prefuse.util.ui.BrowserLauncher;
import prefuse.visual.VisualItem;
import prefuse.visual.sort.ItemSorter;

public class Example {
	public static void main(String argv[]) {
		// System.out.println("Hello world!");
		// ��һ���ǽ����ӻ�����Ҫ�����ݶ���prefuse�ڲ������ݽṹ������ѡ������ڲ���graph�ṹ
		Graph graph = null;
		try {
			graph = new GraphMLReader().readGraph("data/socialnet.xml");// �˲�����ܰ����ܶ�ε�����ת��
		} catch (DataIOException e) {
			e.printStackTrace();
			System.err.println("Erroe");
			System.exit(1);
		}
		System.out.println("The 1 step ending.");
		System.out.println("Begin the 2 step.");
		// ����һ�����ӻ�ͼ�ĳ������ڲ��洢�����ݽṹ��
		Visualization vis = new Visualization();// �˽ṹ����ԭʼ��������µĿ��ӻ���Ϣ���磺x,y���꣬��ɫ����С
		vis.add("graph", graph);
		System.out.println("The 2 step ending.");
		System.out.println("Begin the 3 step.");
		// render��render������������������
		LabelRenderer r = new LabelRenderer("name");// ʹ��name���������б�ǩ�Ľڵ�
		r.setRoundedCorner(8, 8);

		vis.setRendererFactory(new DefaultRendererFactory(r));// ����ͼ����ô������Ҫ����
		System.out.println("The 3 step ending.");
		System.out.println("Begin the 4 step.");
		// ���ݴ�����������ǰ���visualization����
		int[] palette = new int[] { ColorLib.rgb(255, 180, 180),
				ColorLib.rgb(190, 190, 255) };
		DataColorAction fill = new DataColorAction("graph.nodes", "gender",
				Constants.NOMINAL, VisualItem.FILLCOLOR, palette);

		ColorAction text = new ColorAction("graph.nodes", VisualItem.TEXTCOLOR,
				ColorLib.gray(0));

		ColorAction edges = new ColorAction("graph.edges",
				VisualItem.STROKECOLOR, ColorLib.gray(200));

		ActionList color = new ActionList();// ������ǰ������ݴ�����������һ��
		color.add(fill);
		color.add(text);
		color.add(edges);

		ActionList layout = new ActionList(Activity.INFINITY);
		layout.add(new ForceDirectedLayout("graph"));
		layout.add(new RepaintAction());
		vis.putAction("color", color);
		vis.putAction("layout", layout);

		System.out.println("The 4 step ending.");
		System.out.println("Begin the 5 step.");

		// ��ʾ�ͽ�������
		Display display = new Display(vis);// ��ʾ���ӻ�����
		display.setSize(720, 500);
		display.addControlListener(new DragControl());
		display.addControlListener(new PanControl());
		display.addControlListener(new ZoomControl());
		System.out.println("The 5 step ending.");
		System.out.println("Begin the 6 step.");
		// ��ʾ�����ӻ�Ч��
		JFrame frame = new JFrame("prefuse example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(display);
		frame.pack();// �����в������
		frame.setVisible(true);
		vis.run("color");
		vis.run("layout");

	}
}