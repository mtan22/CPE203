import org.w3c.dom.Node;

import java.util.*;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class AStarPathingStrategy
        implements PathingStrategy
{


    public List<Point> computePath(Point start, Point end,
                                   Predicate<Point> canPassThrough,
                                   BiPredicate<Point, Point> withinReach,
                                   Function<Point, Stream<Point>> potentialNeighbors)
    {

        List<Point> path = new ArrayList<Point>();

        //checker to see if at end
        boolean reachingEnd = false;

        Comparator<Point> fCOMP = Comparator.comparingInt(Point::getFval);

        PriorityQueue<Point> open_list = new PriorityQueue<Point>(fCOMP);
        HashSet<Point> closed_list = new HashSet<Point>();

        HashMap<Point, Integer> Open_G = new HashMap<Point, Integer>();
        HashMap<Point, Point> prev = new HashMap<Point, Point>();


        //Check if start equals end -- no real path
        if (start.equals(end)) {
            return path;
        }


        //Add start to list
        open_list.add(start);
        Point curr = null;

        Open_G.put(start, 0);


        //Analyze all valid adjacent
//        while (!open_list.isEmpty()) {
        while (open_list.size() > 0) {

            curr = open_list.poll();
            //check to see if end is within reach
            if (withinReach.test(curr, end)) {
                prev.put(end, curr);
                reachingEnd = true;
                //Go construct
                break;

            }

            else {
                //List<Point> neighboring_points = potentialNeighbors.apply(curr).collect(Collectors.toList());
                List<Point> neighboring_points = potentialNeighbors.apply(curr).filter(canPassThrough).filter(p -> !p.equals(start) && !p.equals(end)).collect(Collectors.toList());

                //If the calculated g value is better than a previously calculated g value, replace the old g value with the new one
                for (Point n : neighboring_points) {
                    if (!closed_list.contains(n)) {

                        //Calculate g
                        int Gval = Open_G.getOrDefault(curr, Integer.MAX_VALUE) + 1;
                        int checker = Open_G.getOrDefault(n, Integer.MAX_VALUE);

                        if (Gval < checker) {
                            Open_G.put(n, Gval);
                            prev.put(n, curr);
                        }

                        if (!open_list.contains(n)) {

                            //Estimate distance of adjacent node to the end point (h)
                            int Hval = Math.abs(n.getX() - end.getX()) + Math.abs(n.getY() - end.getY());

                            //Use Hval
                            //Add g and h to get f value
                            //Mark the neighbor as the current
                            int Fval = Open_G.getOrDefault(n, Integer.MAX_VALUE) + Hval;
                            n.setFval(Fval);
                            open_list.add(n);

                        }
                    }
                }
            }


            //Move the current to the closed list
            closed_list.add(curr);


        }


        //If at end -- Construct
        if (reachingEnd) {
            //set current equal to end
            curr = end;
            //while prev contains the current (ie. end), set current equal to that point. If said point isn't start, add it to the path.
            while (prev.containsKey(curr)) {
                curr = prev.get(curr);
                if (!curr.equals(start)) {
                    path.add(0, curr);
                }
            }
        }

        return path;

    }

}


