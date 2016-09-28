# ValueObjectBase
Provides Value Object base that eliminates some of the verbose coding that goes into value object class definitions.

Ever since working with Scala, I've always been jealous of how simple and concise value 
objects are in Scala as opposed to what we put up with using Java.  To give you an
idea, here's a Scala example:

```  
case class ThreadStack(id:String, name:String, state:Thread.State, monitorId: String, executionPointList:List[ExecutionPoint], lockedSunchronizerList:List[LockedResource])
case class ExecutionPoint(className:String, methodName:String, sourceFileName:String, lineNbr:Integer, blockedOn: LockedResource, lockedResourceList:List[LockedResource])
case class LockedResource(monitorLockName:String, lockedClassName:String)
case class HotSpot(className:String, methodName:String, sourceFileName:String, nbrMentions:Int)
```  

Scala provides convenient implementations for toString(), hashcode(), equals(), and all accessors for free. 
As case classes in Scala are immutable, mutators aren't provided.

This is an advantage for several reasons. first, code is read much more often than it's written. Even
though modern day IDEs will generate accessors and mutators for you, that doesn't get us out of reading them
from time to time as we debug code. Most equals(), hashcode(), clone(), and toString() implementations are simple, 
but reading them while debugging takes valuable time.

Scala also provides the convenience of named parameters, which get Scala programmers out of coding numerous
constructors with various combinations of parameters. While it's not possible to override Java language 
restrictions and provide named parameters for constructors, we can come relatively close. 

System Requirements
==================
* Java JDK 7.0 or above.  
* Apache Commons Lang version 3.0 or above  

Installation Instructions
==================
BeanTester is easy to install whether you use maven or not.

### Maven Users  
Maven users can find dependency information [here](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22org.force66%22%20AND%20a%3A%22ValueObjectBase%22).

Usage
==================

To start using ValueObjectBase, just extend ValueObjectBase like the example below.

```  
public class ThreadStack extends ValueObjectBase {
... // code omitted for brevity.
}
```  

At this point, equals(), hashcode(), toString(), and clone() all work.

### Named parameter constructors

While it's not possible for ValueObjectBase to provide named parameters to abbreviate constructor code, it can provide most of the benefit with it's init() method.  Here's a sample:

```  
ThreadStack stack = new ThreadStack().init(
    Arg.field("name").is("com.jmu.MyClass"),
    Arg.field("id").is("@123dfg45"),
    Arg.field("state").is(Thread.State.RUNNING),
    Arg.field("monitorId").is("alsdiufhsdalifuhdsfs")
);
```  
 
### Selective Cloning

Scala provides a convenient copy() function that accepts named parameters that will copy all fields, but override
values provided in the argument list. For example, threadStack.copy(name="com.jmu.MyOtherClass") in scala will copy all fields
of the underlying class but override the value of the "name" field with the value provided.

ValueObjectBase provides the same as Scala's copy() as illustrated below:
```  
threadStack.copy(
    Arg.field("name").is("com.jmu.MyOtherClass")
);
```  

### Options for Verbose Getters and Setters

ValueObjectBase can't provide value here. However, if you provide both getters and setters, you're essentially making those fields publicly available. You can eliminate much coding by acknowledging that fact and declaring them public to begin with. this would be considered heresy in may circles, but field security is essentially as effective as it is declaring them private
and providing both getters and setters for all fields. Not quite as concise as Scala, but closer.

```  
public class ThreadStack extends ValueObjectBase {
    public String       id;
    public String       name;
    public Thread.State state;
    public String       monitorId;
}
```  