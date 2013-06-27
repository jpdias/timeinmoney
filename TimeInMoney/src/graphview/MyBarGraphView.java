package graphview;

import graphview.GraphViewSeries.GraphViewSeriesStyle;
import android.content.Context;
import android.graphics.Canvas;

public class MyBarGraphView extends BarGraphView {

	@Override
	public void setHorizontalLabels(String[] horlabels) {
		// TODO Auto-generated method stub
		super.setHorizontalLabels(horlabels);
	}

	public float colwidth = 10;
	@Override
	public void drawSeries(Canvas canvas, GraphViewData[] values,
			float graphwidth, float graphheight, float border, double minX,
			double minY, double diffX, double diffY, float horstart,
			GraphViewSeriesStyle style) {

		float colwidth1 = (graphwidth ) / values.length;
		
		paint.setStrokeWidth(style.thickness);
		paint.setColor(style.color);

		// draw data
		for (int i = 0; i < values.length; i++) {
			float valY = (float) (values[i].valueY - minY);
			float ratY = (float) (valY / diffY);
			float y = graphheight * ratY;

			// hook for value dependent color
			if (style.getValueDependentColor() != null) {
				paint.setColor(style.getValueDependentColor().get(values[i]));
			}

			canvas.drawRect((i * colwidth1) + horstart, (border - y) + graphheight, ((i * colwidth1) + horstart) + (colwidth - 1), graphheight + border - 1, paint);
		}
	}

	public MyBarGraphView(Context context, String title) {
		super(context, title);
	}

}
