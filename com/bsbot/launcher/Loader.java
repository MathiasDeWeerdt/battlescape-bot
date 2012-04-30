package com.bsbot.launcher;

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLConnection;

import com.bsbot.hooks.Client;
import com.bsbot.input.Mouse;

public class Loader extends Applet
    implements MouseListener, Runnable
{
	


    public Loader()
    {
        _flddo = false;
        a = 474;
    }
    
    public Applet getApplet(){
    	return _fldfor;
    }
    
    protected boolean focused;
    protected int width;
    protected int height;
    public Point mousePos;
    
    public final synchronized void processEvent(AWTEvent e)
    {
        if(e.getID() == 1004 && !hasFocus())
        {
            focused = true;
            super.processEvent(e);
            return;
        }
        if(e.getID() == 501 && !hasFocus())
            requestFocus();
        if(e instanceof MouseEvent)
        {
            MouseEvent event = (MouseEvent)e;
            if(!Mouse.input && !event.getSource().equals(Mouse.FAKE_SOURCE))
                return;
            if(e.getID() == 501)
                Mouse.pressed = true;
            else
            if(e.getID() == 502)
                Mouse.pressed = false;
            if(event.getSource().equals(Mouse.FAKE_SOURCE))
                event.setSource(this);
            mousePos = event.getPoint();
        }
        super.processEvent(e);
    }

    public void requestFocus()
    {
        focused = true;
        super.requestFocus();
    }


    public byte[] a(String s, String s1)
        throws IOException
    {
        URL url = new URL(s);
        URLConnection urlconnection = url.openConnection();
        int i = urlconnection.getContentLength();
        BufferedInputStream bufferedinputstream = new BufferedInputStream(urlconnection.getInputStream());
        ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
        byte abyte0[] = new byte[4096];
        boolean flag = false;
        do
        {
            int j;
            if((j = bufferedinputstream.read(abyte0, 0, abyte0.length)) == -1)
                break;
            if(j != 0)
            {
                bytearrayoutputstream.write(abyte0, 0, j);
                int k = (int)(((double)bytearrayoutputstream.size() / (double)i) * 100D);
                String s2 = (s1 == null ? "Loading" : s1) + " - " + k + "%";
                a(s2);
            }
        } while(true);
        bufferedinputstream.close();
        byte abyte1[] = bytearrayoutputstream.toByteArray();
        bytearrayoutputstream.close();
        return abyte1;
    }

    public void a()
    {
        try
        {
            ClassThing b1 = new ClassThing();
            byte abyte0[] = a("https://dl.dropbox.com/s/sbvo1ozdxw66wbd/client_V1.1.jar?dl=1", "Starting up BSBot"); // now with grounditems!
            if(abyte0 == null)
                throw new Exception("Client download failed");
            b1.a = new ClientClassLoader(abyte0);
            Class class1 = b1.loadClass("sign.signlink");
            class1.getField("mainapp").set(null, this);
            Class class2 = b1.loadClass("client");
            _fldfor = (Applet)class2.newInstance();
            _fldfor.init();
            _fldfor.start();
            BSLoader.setClient((Client)_fldfor);
        }
        catch(Exception exception)
        {
            String s = exception.getMessage();
            if(s == null)
                s = "null";
            a(s);
            exception.printStackTrace();
        }
    }

    public void a(String s)
    {
        Font font = new Font("Helvetica", 1, 13);
        FontMetrics fontmetrics = getFontMetrics(font);
        Graphics g = getGraphics();
        if(g != null)
        {
            g.setColor(Color.black);
            g.fillRect(0, 0, getSize().width, getSize().height);
            g.setColor(new Color(0x8c1111));
            g.drawRect(getSize().width / 2 - 152, getSize().height / 2 - 18, 303, 33);
            g.setFont(font);
            g.setColor(Color.white);
            g.drawString(s, (getSize().width - fontmetrics.stringWidth(s)) / 2, getSize().height / 2 + 4);
            g.dispose();
        }
        repaint();
    }

    public void run()
    {
        while(super.getWidth() == 0 || super.getHeight() == 0) 
            try
            {
                Thread.sleep(1000L);
            }
            catch(Exception exception) { }
        _fldif = new BufferedImage(super.getWidth(), super.getHeight(), 2);
        super.addMouseListener(this);
    }

    public void init()
    {
        (new Thread(this)).start();
    }

    public void start()
    {
        if(_fldfor != null)
            _fldfor.start();
    }

    public void stop()
    {
        if(_fldfor != null)
            _fldfor.stop();
    }

    public void destroy()
    {
        if(_fldfor != null)
        {
            _fldfor.destroy();
            _fldfor = null;
        }
    }

    public String getParameter(String s)
    {
        if(s.equals("lowmem"))
            return _flddo ? "1" : "0";
        if(s.equals("version"))
            return Integer.toString(a);
        else
            return super.getParameter(s);
    }

    public void paint(Graphics g)
    {
        if(_fldif == null)
        {

            if(_fldfor != null){
                return;
            }
        } else
        {
            Graphics g1 = _fldif.getGraphics();
            g1.setColor(Color.black);
            g1.clearRect(0, 0, _fldif.getWidth(), _fldif.getHeight());
            int i = _fldif.getWidth() / 2 - 200;
            int j = _fldif.getHeight() / 2 - 150;
            g1.setColor(Color.blue);
            g1.drawRect(i - 15, j - 15, 400, 20);
            g1.setColor(Color.yellow);
            g1.drawString("Memory Usage: " + (_flddo ? "Low" : "High"), i + 125, j);
            j += 100;
            g1.setColor(Color.blue);
            g1.drawRect(i - 15, j - 15, 400, 20);
            g1.setColor(Color.yellow);
            g1.drawString("Version (Player Models, Animations, & Graphics): " + a, i, j);
            j += 100;
            g1.setColor(Color.blue);
            g1.drawRect(i - 15, j - 15, 400, 20);
            g1.setColor(Color.white);
            g1.drawString("Start Client", i + 150, j);
            g1.dispose();
            g.drawImage(_fldif, 0, 0, null);
            return;
        }
    }

    public void mouseClicked(MouseEvent mouseevent)
    {
        if(_fldif == null)
            return;
        int i = mouseevent.getX();
        int j = mouseevent.getY();
        int k = _fldif.getWidth() / 2 - 200;
        int l = _fldif.getHeight() / 2 - 150;
        if(j >= l - 30 && j <= l + 20)
        {
            _flddo = !_flddo;
            super.repaint();
            return;
        }
        l += 100;
        if(j >= l - 30 && j <= l + 20)
        {
            if(a == 474)
                a = 525;
            else
            if(a == 525)
                a = 562;
            else
            if(a == 562)
                a = 603;
            else
            if(a == 603)
                a = 474;
            super.repaint();
            return;
        }
        l += 100;
        if(j >= l - 30 && j <= l + 20)
        {
            _fldif = null;
            super.removeMouseListener(this);
            a();
            return;
        } else
        {
            return;
        }
    }

    public void mouseEntered(MouseEvent mouseevent)
    {
    }

    public void mouseExited(MouseEvent mouseevent)
    {
    }

    public void mousePressed(MouseEvent mouseevent)
    {
    }

    public void mouseReleased(MouseEvent mouseevent)
    {
    }

    private Applet _fldfor;
    private boolean _flddo;
    private int a;
    private BufferedImage _fldif;
    
    public final BufferedImage GAME_BUFFER = new BufferedImage(765, 503, 1);
    public final BufferedImage BOT_BUFFER = new BufferedImage(765, 503, 1);

   /* private Applet _fldfor;
    private boolean _flddo;
    private int a;
    private BufferedImage _fldif;*/
}
