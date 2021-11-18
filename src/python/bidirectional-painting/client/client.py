from tkinter import *
from tkinter import ttk, colorchooser
import grpc
import sys, os
sys.path.insert(0, '../protos')
import transform_pb2_grpc
from transform_pb2 import Point, Mouse, Canvas as CanvasGrpc

class main:
    def __init__(self,master):
        channel = grpc.insecure_channel("localhost:50051")
        self.stub = transform_pb2_grpc.TransformStub(channel)
        self.master = master
        self.color_fg = '#000000'
        self.color_bg = '#d8d8d8'
        self.old_x = None
        self.old_y = None
        self.old_received_x = None
        self.old_received_y = None
        self.penwidth = 5
        self.drawWidgets()
        self.c.bind('<B1-Motion>',self.paint)#drwaing the line 
        self.c.bind('<ButtonRelease-1>',self.reset)

    def paint(self,e):
        canvas_width = self.c.winfo_width()
        canvas_height = self.c.winfo_height()
        color = self.color_fg
        x = e.x
        y = e.y
        iterator = iter([Point(mouse= Mouse(x=x,y=y), canvas=CanvasGrpc(width=canvas_width, height=canvas_height), color=color)])
        
        if self.old_x and self.old_y:
            self.c.create_line(self.old_x, self.old_y, e.x, e.y, 
                                width=self.penwidth, fill=color,capstyle=ROUND, smooth=True)
        self.old_x = x
        self.old_y = y
        
        response = self.stub.Transform(iterator)
        # Print gRPC line
        for r in response:
            if self.old_received_x and self.old_received_y:
                self.c.create_line(self.old_received_x, self.old_received_y, r.x, r.y, 
                                width=self.penwidth, fill=r.color,capstyle=ROUND, smooth=True)
            self.old_received_x = r.x
            self.old_received_y = r.y

    def reset(self,e):    #reseting or cleaning the canvas 
        self.old_x = None
        self.old_y = None
        self.old_received_x = None
        self.old_received_y = None   

    def clear(self):
        self.c.delete(ALL)

    def change_fg(self):  #changing the pen color
        self.color_fg=colorchooser.askcolor(color=self.color_fg)[1]

    def change_bg(self):  #changing the background color canvas
        self.color_bg=colorchooser.askcolor(color=self.color_bg)[1]
        self.c['bg'] = self.color_bg

    def drawWidgets(self):
        self.controls = Frame(self.master,padx = 5,pady = 5)
        
        self.c = Canvas(self.master,width=500,height=400,bg=self.color_bg)
        self.c.pack(fill=BOTH,expand=True)

        menu = Menu(self.master)
        self.master.config(menu=menu)
        Menu(menu)
        colormenu = Menu(menu)
        menu.add_cascade(label='Colors',menu=colormenu)
        colormenu.add_command(label='Brush Color',command=self.change_fg)
        colormenu.add_command(label='Background Color',command=self.change_bg)
        optionmenu = Menu(menu)
        menu.add_cascade(label='Options',menu=optionmenu)
        optionmenu.add_command(label='Clear Canvas',command=self.clear)
        optionmenu.add_command(label='Exit',command=self.master.destroy) 
        
        

if __name__ == '__main__':
    root = Tk()
    main(root)
    root.title('Application')
    root.mainloop()
