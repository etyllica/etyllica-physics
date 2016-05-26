package br.com.etyllica.physics;

import java.awt.geom.AffineTransform;

import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;

import br.com.etyllica.core.graphics.Graphics;
import br.com.etyllica.layer.Layer;

/**
 * Layer envelope to use physics
 * 
 * @author yuripourre
 *
 */

public class RigidBody extends Body {
	
	private Layer layer;
	
	public RigidBody(Layer layer) {
		super();
		this.layer = layer;
		
		addFixture(new BodyFixture(new org.dyn4j.geometry.Rectangle(layer.getW()*layer.getScaleX(), layer.getH()*layer.getScaleY())));
		translate(layer.getX()+(layer.getW()*layer.getScaleX())/2, layer.getY()+(layer.getH()*layer.getScaleY())/2);
		
		//setMass(MassType.NORMAL);
		setMass();
	}
	
	public void draw(Graphics g) {
		
		AffineTransform transform = new AffineTransform();
		
		float initialX = layer.getX()+layer.getW()/2;
		float initialY = layer.getY()+layer.getH()/2;
						
		transform.translate(this.transform.getTranslationX()-initialX, this.transform.getTranslationY()-initialY);
		transform.concatenate(AffineTransform.getRotateInstance(this.transform.getRotation(),layer.getX()+layer.getW()/2, layer.getY()+layer.getH()/2));
		
		g.setTransform(transform);
		
		this.layer.simpleDraw(g);
		
		g.resetTransform();
	}
	
	public Layer getLayer() {
		return layer;
	}
		
}
