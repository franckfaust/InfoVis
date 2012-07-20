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
		// 第一步是将可视化化需要的数据读入prefuse内部的数据结构，此例选择的是内部的graph结构
		Graph graph = null;
		try {
			graph = new GraphMLReader().readGraph("data/socialnet.xml");// 此步骤可能包括很多次的数据转换
		} catch (DataIOException e) {
			e.printStackTrace();
			System.err.println("Erroe");
			System.exit(1);
		}
		System.out.println("The 1 step ending.");
		System.out.println("Begin the 2 step.");
		// 创建一个可视化图的抽象概念（内部存储的数据结构）
		Visualization vis = new Visualization();// 此结构包括原始数据域和新的可视化信息，如：x,y坐标，颜色，大小
		vis.add("graph", graph);
		System.out.println("The 2 step ending.");
		System.out.println("Begin the 3 step.");
		// render和render工厂，用来传递数据
		LabelRenderer r = new LabelRenderer("name");// 使用name来创建带有标签的节点
		r.setRoundedCorner(8, 8);

		vis.setRendererFactory(new DefaultRendererFactory(r));// 决定图形怎么画的主要工具
		System.out.println("The 3 step ending.");
		System.out.println("Begin the 4 step.");
		// 数据处理动作，高于前面的visualization处理
		int[] palette = new int[] { ColorLib.rgb(255, 180, 180),
				ColorLib.rgb(190, 190, 255) };
		DataColorAction fill = new DataColorAction("graph.nodes", "gender",
				Constants.NOMINAL, VisualItem.FILLCOLOR, palette);

		ColorAction text = new ColorAction("graph.nodes", VisualItem.TEXTCOLOR,
				ColorLib.gray(0));

		ColorAction edges = new ColorAction("graph.edges",
				VisualItem.STROKECOLOR, ColorLib.gray(200));

		ActionList color = new ActionList();// 用来将前面的数据处理动作集合在一起
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

		// 显示和交互控制
		Display display = new Display(vis);// 显示可视化数据
		display.setSize(720, 500);
		display.addControlListener(new DragControl());
		display.addControlListener(new PanControl());
		display.addControlListener(new ZoomControl());
		System.out.println("The 5 step ending.");
		System.out.println("Begin the 6 step.");
		// 显示出可视化效果
		JFrame frame = new JFrame("prefuse example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(display);
		frame.pack();// 窗口中布局组件
		frame.setVisible(true);
		vis.run("color");
		vis.run("layout");

	}
}