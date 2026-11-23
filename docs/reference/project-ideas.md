# CSC110 Final Project — Project Ideas

Pick **one**. All six are Java Swing desktop apps with about the same amount of work. Choose the one you'd most enjoy demoing.

> **Every project must:** store its data in classes/objects and `ArrayList`s, keep data consistent when things change,
> reject bad input with clear messages, support search/filter, show calculated summaries, and **save and reopen** everything.

---

## 1. Road Trip Planner
Build a planner for travelers who want to see whether a multi-day trip fits their time and budget. Changing a stop should update the rest of the plan.

Your planner must let travelers set a trip budget and daily travel-time limit, then:

- Add, edit, remove, and reorder stops. Each stop has a name, day number, category, and activity cost.
- Enter distance and travel time between consecutive stops. Reject negative values and connections to missing stops. If stops change, flag outdated travel estimates instead of using them as current information.
- Search stops by name and filter by day or category.
- See total distance, travel time, planned cost, and remaining budget. Flag trips over budget and days over the travel-time limit. Clearly show when estimates are incomplete.
- Save and reopen the trip with its stops, order, travel estimates, budget, and daily limit intact.

Use fictional or manually entered travel estimates. Live maps, bookings, and automatic route optimization are not required.

---

## 2. Escape Room Adventure
Create a small escape-room story where discovering clues and using items changes what the player can do next.

Your game must include:

- At least five connected rooms, three usable items, and three puzzles. Show room descriptions, available exits, and discovered clues.
- Collecting and using items, moving between rooms, and entering puzzle answers. At least one item unlocks access, and one puzzle depends on an earlier discovery.
- Clear feedback for invalid moves, unavailable items, and incorrect actions. Solved puzzles cannot award the same reward again.
- Inventory search by name or a puzzle filter by solved status, plus a progress summary based on actual discoveries.
- A reachable escape ending that requires all three puzzles to be solved.
- Saving and reopening the current room, inventory, puzzle states, and changes to the world.

You choose the setting and puzzles. Text descriptions with graphical controls are enough; illustrations, combat, and a room editor are optional.

---

## 3. Arcade Game
Build Snake or tic-tac-toe. Players should be able to play again, review past results, and resume an unfinished game.

Your game must include:

- Correct rules and visible game state. Snake needs movement, food, growth, scoring, and wall/body collision rules. Tic-tac-toe needs legal moves, alternating turns, row/column/diagonal wins, and draws.
- Two modes. Snake uses solid walls or wraparound edges. Tic-tac-toe uses two local players or a computer opponent that makes legal moves.
- Restarting without deleting past results.
- A record of completed games: player names, mode, result, and move history. Let players filter results by player or mode and review a game's moves.
- Calculated scores or win/draw/loss totals. Count each completed game once.
- Saving and reopening the unfinished game and past results, including everything needed to resume the next move.

Snake can advance one step per button/key action; real-time movement is optional. A computer opponent may choose random legal moves. Sound, animation, and online multiplayer are optional.

---

## 4. Sports League Manager
Build a tool for someone running a recreational league. They need rosters, games, results, and standings to stay consistent when information changes.

Your manager must let the organizer:

- Create and edit teams and player rosters. Each player belongs to one team; reject duplicate membership and avoid links to missing players or teams.
- Schedule two different teams in an integer time slot. A team cannot play itself or appear in two games in the same slot.
- Enter and correct completed-game scores.
- Search teams or players and filter games by team, slot, or completion status. Show a team's roster and schedule together.
- View standings calculated from the results: wins, losses, draws where relevant, and league points. Correcting a score must update standings without counting a game twice.
- Save and reopen rosters, schedules, results, and league settings.

Choose one sport and document its scoring rules. Manual scheduling with numbered slots is enough; automatic schedules, calendars, and live sports data are optional.

---

## 5. Coffee Shop Order System
Build an ordering system for a small coffee shop. Customers build and review orders, staff manage the queue, and the owner sees completed sales.

Your system must let users:

- Configure menu items, prices, sizes, paid add-ons, and availability.
- Build an order with multiple drink lines and quantities, then edit it before submission. Calculate line prices and the total from the chosen options.
- Reject invalid combinations, nonpositive quantities, and unavailable items. A submitted order cannot be edited or submitted again, and later menu-price changes cannot change its agreed prices.
- Move orders through submitted, preparing, and ready statuses using rules you document. Find an order by number or filter the queue by status.
- View a receipt and a sales summary with completed-order count and sales totals. Use ready orders and count each once.
- Save and reopen the menu, orders, drink selections, agreed prices, and statuses.

Customer and staff panels can be part of the same app. Payments, accounts, and online deployment are optional.

---

## 6. Dealer's Choice
Your own idea, in Java Swing. **Talk to me about it first. I have to approve it before you build.**

It must be about as big as the five above, with **all** of these:
1. **3+ kinds of data objects** (classes) in `ArrayList`s, linked to each other (e.g. a Loan links a Member and a Book)
2. **Add, edit, remove** for your main data, with **validation** (bad or duplicate input rejected with clear messages)
3. **2+ consistency rules** that stay true when data changes (e.g. "a book can't be loaned twice")
4. **Search** by name + **at least one filter**
5. **Calculated summaries** (totals, counts, standings) that update and never double-count
6. **Save and reopen** everything exactly as it was
7. A **demo story** you can show in 3–5 minutes

Not allowed: recreations of existing commercial games, apps that need internet/live data, or apps that are mostly
art/graphics with little data or rules. The **Example Project (Plant Care Tracker)** is an approved Dealer's Choice.
