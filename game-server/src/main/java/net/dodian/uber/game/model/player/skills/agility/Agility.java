package net.dodian.uber.game.model.player.skills.agility;

import net.dodian.uber.game.Server;
import net.dodian.uber.game.event.Event;
import net.dodian.uber.game.event.EventManager;
import net.dodian.uber.game.model.UpdateFlag;
import net.dodian.uber.game.model.entity.npc.Npc;
import net.dodian.uber.game.model.entity.player.Client;
import net.dodian.uber.game.model.player.packets.outgoing.SendMessage;
import net.dodian.uber.game.model.player.skills.Skill;
import net.dodian.utilities.Misc;

public class Agility {
    final Client c;

    public Agility(Client c) {
        this.c = c;
    }

    public void giveEndExperience(int xp) {
        c.giveExperience(xp, Skill.AGILITY);
        c.triggerRandom(xp);
    }

    public void GnomeLog() {
        if (c.UsingAgility) {
            return;
        }
        if (c.getPosition().getX() == 2474 && c.getPosition().getY() == 3436) {
            c.UsingAgility = true;
            final int oldEmote = c.getWalkAnim();
            c.setWalkAnim(762);
            int time = 4800;
            c.AddToCords(0, -7, time);
            EventManager.getInstance().registerEvent(new Event(time) {
                public void execute() {
                    if (c.disconnected) {
                        stop();
                        return;
                    }
                    c.setWalkAnim(oldEmote);
                    giveEndExperience(280);
                    c.agilityCourseStage = c.agilityCourseStage >= 0 ? 1 : c.agilityCourseStage;
                    c.UsingAgility = false;
                    stop();
                }
            });
        }
    }

    public void GnomeNet1() {
        Npc npc = Server.npcManager.getNpc(Server.npcManager.gnomeSpawn + 0); //first npc!
        if (c.UsingAgility) {
            return;
        }
        if ((c.getPosition().getX() < 2471 && c.getPosition().getX() > 2476) || c.getPosition().getY() != 3426) {
            return;
        }
        c.UsingAgility = true;
        npc.setText("My mom is faster than you!");
        c.requestAnim(828, 0);
        c.walkBlock = System.currentTimeMillis() + 600;
        EventManager.getInstance().registerEvent(new Event(600) {
            public void execute() {
                if (c.disconnected) {
                    stop();
                    return;
                }
                c.teleportTo(2473, 3424, 1);
                giveEndExperience(150);
                c.agilityCourseStage = c.agilityCourseStage >= 1 ? 2 : c.agilityCourseStage;
                c.UsingAgility = false;
                stop();
            }
        });
    }

    public void GnomeTree1() {
        Npc npc = Server.npcManager.getNpc(Server.npcManager.gnomeSpawn + 1); //second npc!
        if (c.UsingAgility) {
            return;
        }
        c.UsingAgility = true;
        npc.setText("Haha you suck at this simple obstacle!");
        c.requestAnim(828, 0);
        c.walkBlock = System.currentTimeMillis() + 600;
        c.agilityCourseStage = c.agilityCourseStage >= 2 ? 3 : c.agilityCourseStage;
        EventManager.getInstance().registerEvent(new Event(600) {
            public void execute() {
                if (c.disconnected) {
                    stop();
                    return;
                }
                c.teleportTo(2473, 3420, 2);
                giveEndExperience(50);
                c.agilityCourseStage = c.agilityCourseStage >= 2 ? 3 : c.agilityCourseStage;
                c.UsingAgility = false;
                stop();
            }
        });
    }

    public void GnomeRope() {
        Npc npc = Server.npcManager.getNpc(Server.npcManager.gnomeSpawn + 2); //third npc!
        if (c.UsingAgility) {
            return;
        }
        if (c.getPosition().getX() != 2477 || c.getPosition().getY() != 3420) {
            return;
        }
        c.UsingAgility = true;
        npc.setText("I do not know why you bother. HAHA!");
        final int oldEmote = c.getWalkAnim();
        c.setWalkAnim(762);
        int time = 4800;
        c.AddToCords(6, 0, time);
        EventManager.getInstance().registerEvent(new Event(time) {
            public void execute() {
                if (c.disconnected) {
                    stop();
                    return;
                }
                c.setWalkAnim(oldEmote);
                giveEndExperience(250);
                c.agilityCourseStage = c.agilityCourseStage >= 3 ? 4 : c.agilityCourseStage;
                c.UsingAgility = false;
                stop();
            }
        });
    }

    public void GnomeTreebranch2() {
        Npc npc = Server.npcManager.getNpc(Server.npcManager.gnomeSpawn + 3); //fourth npc!
                if (c.UsingAgility) {
                    return;
                }
                c.UsingAgility = true;
                npc.setText("To darn easy.");
                c.requestAnim(828, 0);
                c.walkBlock = System.currentTimeMillis() + 600;
                EventManager.getInstance().registerEvent(new Event(600) {
                    public void execute() {
                if (c.disconnected) {
                    stop();
                    return;
                }
                c.teleportTo(2485, 3421, 0);
                giveEndExperience(50);
                c.agilityCourseStage = c.agilityCourseStage >= 4 ? 5 : c.agilityCourseStage;
                c.UsingAgility = false;
                stop();
            }
        });
    }

    public void GnomeNet2() {
        Npc npc = Server.npcManager.getNpc(Server.npcManager.gnomeSpawn + 4); //fifth npc!
        if (c.UsingAgility) {
            return;
        }
        if ((c.getPosition().getX() < 2483 && c.getPosition().getX() > 2488) || c.getPosition().getY() != 3425) {
            return;
        }
        c.UsingAgility = true;
        npc.setText("net profit of zero effort.");
        c.requestAnim(828, 0);
        c.walkBlock = System.currentTimeMillis() + 600;
        EventManager.getInstance().registerEvent(new Event(600) {
            public void execute() {
                if (c.disconnected) {
                    stop();
                    return;
                }
                c.teleportTo(c.getPosition().getX(), c.getPosition().getY() + 2, 0);
                giveEndExperience(150);
                c.agilityCourseStage = c.agilityCourseStage >= 5 ? 6 : c.agilityCourseStage;
                c.UsingAgility = false;
                stop();
            }
        });
    }

    public void GnomePipe() {
        Npc npc = Server.npcManager.getNpc(Server.npcManager.gnomeSpawn + 5); //last npc!
        if (c.UsingAgility) {
            return;
        }
        c.UsingAgility = true;
        final int oldEmote = c.getWalkAnim();
        npc.setText("Pipe it down...You are nothing special!");
        c.setWalkAnim(746);
        c.AddToCords(0, 7, 4200);
        EventManager.getInstance().registerEvent(new Event(600) {
            int part = 0;

            public void execute() {
                if (c.disconnected) {
                    stop();
                    return;
                }
                part++;
                if (part == 7) {
                    c.setWalkAnim(oldEmote);
                    c.requestAnim(748, 0);
                    if (c.agilityCourseStage == 6) {
                        c.addItem(2996, 1 + Misc.random(c.getLevel(Skill.AGILITY) / 11));
                        c.agilityCourseStage = 0;
                        c.send(new SendMessage("You finished a gnome lap!"));
                        giveEndExperience(1050);
                    } else {
                        giveEndExperience(250);
                    }
                    c.UsingAgility = false;
                    stop();
                } else if (part == 1) {
                    c.setWalkAnim(747);
                    c.getUpdateFlags().setRequired(UpdateFlag.APPEARANCE, true);
                }
            }
        });
    }

    public void BarbRope() {
        if (c.UsingAgility) {
            return;
        }
        if ((c.getPosition().getX() < 2550 && c.getPosition().getX() > 2552) || c.getPosition().getY() != 3554) {
            return;
        }
        if (c.getLevel(Skill.AGILITY) < 40) {
            c.send(new SendMessage("You need level 40 agility to use this!"));
            return;
        }
        c.UsingAgility = true;
        final int oldEmote = c.getWalkAnim();
        c.setWalkAnim(1501);
        int time = 3500;
        c.AddToCords(0, -5, 3500);
        EventManager.getInstance().registerEvent(new Event(time) {
            public void execute() {
                if (c.disconnected) {
                    stop();
                    return;
                }
                c.setWalkAnim(oldEmote);
                giveEndExperience(400);
                c.agilityCourseStage = c.agilityCourseStage >= 0 ? 1 : c.agilityCourseStage;
                c.UsingAgility = false;
                stop();
            }
        });
    }

    public void BarbLog() {
        if (c.UsingAgility) {
            return;
        }
        if (c.getLevel(Skill.AGILITY) < 40) {
            c.send(new SendMessage("You need level 40 agility to use this!"));
            return;
        }
        c.UsingAgility = true;
        int time = 7200;
        final int oldEmote = c.getWalkAnim();
        if (c.getPosition().getY() == 3547 || c.getPosition().getY() == 3545) {
            c.AddToCords(1, 0, time);
            EventManager.getInstance().registerEvent(new Event(600) {
                int stage = 0;

                public void execute() {
                    if (c.disconnected) {
                        stop();
                        return;
                    }
                    stage++;
                    if (stage == 1) {
                        c.AddToCords(0, c.getPosition().getY() == 3547 ? -1 : 1, time);
                        stage++;
                    } else if (stage > 3) {
                        c.setWalkAnim(762);
                        c.AddToCords(-10, 0, time);
                        EventManager.getInstance().registerEvent(new Event(time) {
                            public void execute() {
                                if (c.disconnected) {
                                    stop();
                                    return;
                                }
                                c.setWalkAnim(oldEmote);
                                giveEndExperience(600);
                                c.agilityCourseStage = c.agilityCourseStage >= 1 ? 2 : c.agilityCourseStage;
                                c.UsingAgility = false;
                                stop();
                            }
                        });
                        stop();
                    }
                }
            });
        } else if (c.getPosition().getX() == 2551 && c.getPosition().getY() == 3546) {
            c.setWalkAnim(762);
            c.AddToCords(-10, 0, time);
            EventManager.getInstance().registerEvent(new Event(time) {
                public void execute() {
                    if (c.disconnected) {
                        stop();
                        return;
                    }
                    c.setWalkAnim(oldEmote);
                    giveEndExperience(600);
                    c.agilityCourseStage = c.agilityCourseStage >= 1 ? 2 : c.agilityCourseStage;
                    c.UsingAgility = false;
                    stop();
                }
            });
        }
    }

    public void BarbNet() {
        if (c.UsingAgility) {
            return;
        }
        if (c.getPosition().getX() != 2539 && (c.getPosition().getY() != 3546 || c.getPosition().getY() != 3545)) {
            return;
        }
        if (c.getLevel(Skill.AGILITY) < 40) {
            c.send(new SendMessage("You need level 40 agility to use this!"));
            return;
        }
        c.UsingAgility = true;
        c.requestAnim(828, 0);
        c.walkBlock = System.currentTimeMillis() + 600;
        EventManager.getInstance().registerEvent(new Event(600) {
            public void execute() {
                if (c.disconnected) {
                    stop();
                    return;
                }
                c.teleportTo(c.getPosition().getX() - 2, c.getPosition().getY(), 1);
                giveEndExperience(250);
                c.agilityCourseStage = c.agilityCourseStage >= 2 ? 3 : c.agilityCourseStage;
                c.UsingAgility = false;
                stop();
            }
        });
    }

    public void BarbLedge() {
        if (c.UsingAgility) {
            return;
        }
        if (c.getLevel(Skill.AGILITY) < 40) {
            c.send(new SendMessage("You need level 40 agility to use this!"));
            return;
        }
        c.UsingAgility = true;
        final int oldEmote = c.getWalkAnim();
        c.setWalkAnim(756);
        int time = 2400;
        c.AddToCords(-4, 0, time);
        EventManager.getInstance().registerEvent(new Event(time) {
            public void execute() {
                if (c.disconnected) {
                    stop();
                    return;
                }
                c.setWalkAnim(oldEmote);
                giveEndExperience(350);
                c.agilityCourseStage = c.agilityCourseStage >= 3 ? 4 : c.agilityCourseStage;
                c.UsingAgility = false;
                stop();
            }
        });
    }

    public void BarbStairs() {
        c.requestAnim(828, 0);
        c.teleportTo(c.getPosition().getX(), c.getPosition().getY(), 0);
        c.walkBlock = System.currentTimeMillis() + 600;
    }

    public void BarbFirstWall() {
        if (c.UsingAgility) {
            return;
        }
        if (c.getPosition().getX() != 2535 && c.getPosition().getY() != 3553) {
            return;
        }
        if (c.getLevel(Skill.AGILITY) < 40) {
            c.send(new SendMessage("You need level 40 agility to use this!"));
            return;
        }
        c.UsingAgility = true;
        final int oldEmote = c.getWalkAnim();
        c.setWalkAnim(840);
        int time = 1800;
        c.AddToCords(2, 0, time);
        EventManager.getInstance().registerEvent(new Event(time) {
            public void execute() {
                if (c.disconnected) {
                    stop();
                    return;
                }
                c.setWalkAnim(oldEmote);
                giveEndExperience(100);
                c.agilityCourseStage = c.agilityCourseStage >= 4 ? 5 : c.agilityCourseStage;
                c.UsingAgility = false;
                stop();
            }
        });
    }

    public void BarbSecondWall() {
        if (c.UsingAgility) {
            return;
        }
        if (c.getPosition().getX() != 2535 && c.getPosition().getY() != 3553) {
            return;
        }
        if (c.getLevel(Skill.AGILITY) < 40) {
            c.send(new SendMessage("You need level 40 agility to use this!"));
            return;
        }
        c.UsingAgility = true;
        final int oldEmote = c.getWalkAnim();
        c.setWalkAnim(840);
        int time = 1800;
        c.AddToCords(2, 0, time);
        EventManager.getInstance().registerEvent(new Event(time) {
            public void execute() {
                if (c.disconnected) {
                    stop();
                    return;
                }
                c.setWalkAnim(oldEmote);
                giveEndExperience(100);
                c.agilityCourseStage = c.agilityCourseStage >= 5 ? 6 : c.agilityCourseStage;
                c.UsingAgility = false;
                stop();
            }
        });
    }

    public void BarbFinishWall() {
        if (c.UsingAgility) {
            return;
        }
        if (c.getPosition().getX() != 2535 && c.getPosition().getY() != 3553) {
            return;
        }
        if (c.getLevel(Skill.AGILITY) < 40) {
            c.send(new SendMessage("You need level 40 agility to use this!"));
            return;
        }
        c.UsingAgility = true;
        final int oldEmote = c.getWalkAnim();
        c.setWalkAnim(840);
        int time = 1800;
        c.AddToCords(2, 0, time);
        EventManager.getInstance().registerEvent(new Event(time) {
            public void execute() {
                if (c.disconnected) {
                    stop();
                    return;
                }
                c.setWalkAnim(oldEmote);
                if (c.agilityCourseStage == 6) {
                    c.addItem(2996, 2 + Misc.random(c.getLevel(Skill.AGILITY) / 22));
                    giveEndExperience(1300);
                    c.send(new SendMessage("You finished a barbarian lap!"));
                    c.agilityCourseStage = 0;
                } else {
                    giveEndExperience(100);
                }
                c.UsingAgility = false;
                stop();
            }
        });
    }

    public void WildyPipe() {
        if (c.getPosition().getX() == 3004 && c.getPosition().getY() == 3937) {
            if (c.UsingAgility) {
                return;
            }
            if (c.getLevel(Skill.AGILITY) < 70) {
                c.send(new SendMessage("You need level 70 agility to use this!"));
                return;
            }
            if(c.getPosition().getX() == 3004 && c.getPosition().getY() == 3937) {
                c.UsingAgility = true;
                final int oldWalk = c.getWalkAnim(), oldRun = c.getRunAnim();
                int distance = 13;
                c.requestAnim(746, 0);
                c.setAgilityEmote(747, 747);
                c.AddToCords(0, distance, distance * 600);
                EventManager.getInstance().registerEvent(new Event(600) {
                    int part = 0;

                    public void execute() {
                        if (c.disconnected) {
                            stop();
                            return;
                        }
                        part++;
                        if (part == distance - 1) {
                            c.requestAnim(748, 1);
                            c.setAgilityEmote(oldWalk, oldRun);
                            giveEndExperience(1000);
                            c.agilityCourseStage = c.agilityCourseStage >= 0 ? 1 : c.agilityCourseStage;
                            c.UsingAgility = false;
                            stop();
                        }
                    }
                });
            }
        }
    }

    public void WildyRope() {
        if (c.UsingAgility) {
            return;
        }
        if (c.getLevel(Skill.AGILITY) < 70) {
            c.send(new SendMessage("You need level 70 agility to use this!"));
            return;
        }
        c.UsingAgility = true;
        final int oldWalk = c.getWalkAnim(), oldRun = c.getRunAnim();
        if (c.getPosition().getY() == 3953 && c.getPosition().getX() >= 3003 && c.getPosition().getX() <= 3006) {
            int distance = 3958 - c.getPosition().getY();
            c.setAgilityEmote(1501, 1501);
            c.AddToCords(0, distance, distance * 600);
            EventManager.getInstance().registerEvent(new Event(distance * 600) {
                public void execute() {
                    if (c.disconnected) {
                        stop();
                        return;
                    }
                    EventManager.getInstance().registerEvent(new Event(distance * 600) {
                        public void execute() {
                            if (c.disconnected) {
                                stop();
                                return;
                            }
                            c.setAgilityEmote(oldWalk, oldRun);
                            giveEndExperience(500);
                            c.agilityCourseStage = c.agilityCourseStage >= 1 ? 2 : c.agilityCourseStage;
                            c.UsingAgility = false;
                            stop();
                        }
                    });
                    stop();
                }
            });
        }
    }

    public void WildyStones() {
        boolean bool = false;
        if (c.getPosition().getX() == 3002 && c.getPosition().getY() == 3960) {
            bool = true;
        }
        if (bool) {
            if (c.UsingAgility) {
                return;
            }
            if (c.getLevel(Skill.AGILITY) < 70) {
                c.send(new SendMessage("You need level 70 agility to use this!"));
                return;
            }
            c.UsingAgility = true;
            final int oldEmote = c.getWalkAnim();
            c.setWalkAnim(769);
            c.AddToCords(-6, 0, 4000);
            EventManager.getInstance().registerEvent(new Event(4000) {
                public void execute() {
                    if (c.disconnected) {
                        stop();
                        return;
                    }
                    c.setWalkAnim(oldEmote);
                    giveEndExperience(650);
                    c.agilityCourseStage = c.agilityCourseStage >= 2 ? 3 : c.agilityCourseStage;
                    c.UsingAgility = false;
                    stop();
                }
            });
        }
    }

    public void WildyLog() {
        int time = 5600;
        c.UsingAgility = true;
        final int oldEmote = c.getWalkAnim();
        if (c.getPosition().getY() == 3944 || c.getPosition().getY() == 3946) {
            c.AddToCords(1, 0, time);
            EventManager.getInstance().registerEvent(new Event(600) {
                int stage = 0;

                public void execute() {
                    if (c.disconnected) {
                        stop();
                        return;
                    }
                    stage++;
                    if (stage == 1) {
                        c.AddToCords(0, c.getPosition().getY() == 3944 ? 1 : -1, time);
                        stage++;
                    } else if (stage > 3) {
                        c.setWalkAnim(762);
                        c.AddToCords(-8, 0, time);
                        EventManager.getInstance().registerEvent(new Event(time) {
                            public void execute() {
                                if (c.disconnected) {
                                    stop();
                                    return;
                                }
                                c.setWalkAnim(oldEmote);
                                giveEndExperience(650);
                                c.agilityCourseStage = c.agilityCourseStage >= 3 ? 4 : c.agilityCourseStage;
                                c.UsingAgility = false;
                                stop();
                            }
                        });
                        stop();
                    }
                }
            });
        } else if (c.getPosition().getX() == 3002 && c.getPosition().getY() == 3945) {
            c.setWalkAnim(762);
            c.AddToCords(-8, 0, time);
            EventManager.getInstance().registerEvent(new Event(time) {
                public void execute() {
                    if (c.disconnected) {
                        stop();
                        return;
                    }
                    c.setWalkAnim(oldEmote);
                    giveEndExperience(650);
                    c.agilityCourseStage = c.agilityCourseStage >= 3 ? 4 : c.agilityCourseStage;
                    c.UsingAgility = false;
                    stop();
                }
            });
        }
    }

    public void WildyClimb() {
        if (c.getPosition().getX() >= 2993 && c.getPosition().getX() <= 2996 && c.getPosition().getY() == 3937) {
            if (c.UsingAgility) {
                return;
            }
            if (c.getLevel(Skill.AGILITY) < 70) {
                c.send(new SendMessage("You need level 70 agility to use this!"));
                return;
            }
            c.UsingAgility = true;
            final int oldEmote = c.getWalkAnim();
            c.setWalkAnim(737);
            c.AddToCords(0, -4, 2400);
            EventManager.getInstance().registerEvent(new Event(2400) { //We need this to be 3k?
                public void execute() {
                    if (c.disconnected) {
                        stop();
                        return;
                    }
                    c.setWalkAnim(oldEmote);
                    if (c.agilityCourseStage == 4) {
                        c.addItem(2996, 3 + Misc.random(c.getLevel(Skill.AGILITY) / 33));
                        c.send(new SendMessage("You finished a wilderness lap!"));
                        giveEndExperience(2700);
                        c.agilityCourseStage = 0;
                    } else giveEndExperience(750);
                    c.UsingAgility = false;
                    stop();
                }
            });
        }
    }

    public void orangeBar() {
        if (c.UsingAgility) {
            return;
        }
        if (!c.checkItem(1544)) {
            c.send(new SendMessage("You need a orange key to use these bars!"));
            return;
        }
        int time = c.getPosition().getX() == 2600 || c.getPosition().getX() == 2597 ? 1200 : c.getPosition().getY() == 9488 || c.getPosition().getY() == 9495 ? 600 : 0;
        c.AddToCords(c.getPosition().getX() == 2597 ? 1 : c.getPosition().getX() == 2600 ? -1 : 0, c.getPosition().getY() == 9488 ? 1 : c.getPosition().getY() == 9495 ? -1 : 0, time);
        EventManager.getInstance().registerEvent(new Event(time > 0 ? 600 : 0) {
            int stage = 0;

            public void execute() {
                if (c.disconnected) {
                    stop();
                    return;
                }
                stage++;
                if (time == 1200 && stage < 2) {
                    int x = 0;
                    int y = 0;
                    c.AddToCords(x, y, time);
                    stage++;
                } else {
                    final int oldEmote = c.getWalkAnim();
                    c.setWalkAnim(744);
                    c.UsingAgility = true;
                    int distance = c.getPosition().getY() == 9488 ? 6 : c.getPosition().getY() == 9489 ? 5 : c.getPosition().getY() == 9494 ? -5 : c.getPosition().getY() == 9495 ? -6 : 0;
                    int time = distance == 6 || distance == -6 ? 6 * 600 : distance == 5 || distance == -5 ? 6 * 600 : 0;
                    c.AddToCords(0, distance, time);
                    EventManager.getInstance().registerEvent(new Event(time) {
                        public void execute() {
                            if (c.disconnected) {
                                stop();
                                return;
                            }
                            c.setWalkAnim(oldEmote);
                            c.requestAnim(743, 0);
                            c.UsingAgility = false;
                            stop();
                        }
                    });
                    stop();
                }
            }
        });
    }

    public void yellowLedge() {
        if (c.UsingAgility || c.getPosition().getX() != 2580) {
            return;
        }
        if (!c.checkItem(1545)) {
            c.send(new SendMessage("You need a yellow key to use this ledge!"));
            return;
        }
        int distance = c.getPosition().getY() == 9512 ? 8 : c.getPosition().getY() == 9520 ? -8 : 0;
        if (distance == 0) {
            return;
        }
        c.UsingAgility = true;
        final int oldEmote = c.getWalkAnim();
        c.setWalkAnim(distance == 8 ? 756 : 754);
        int time = 5400;
        c.AddToCords(0, distance, time);
        EventManager.getInstance().registerEvent(new Event(time) {
            public void execute() {
                if (c.disconnected) {
                    stop();
                    return;
                }
                c.setWalkAnim(oldEmote);
                c.UsingAgility = false;
                stop();
            }
        });
    }

    public void kbdEntrance() {
        if (c.UsingAgility) {
            return;
        }
        int distance = c.getPosition().getX() == 3304 ? 1 : c.getPosition().getX() == 3305 ? -1 : 0;
        if (distance == 0) {
            return;
        }
        c.UsingAgility = true;
        c.ReplaceObject(3305, 9376, 6452, -3, 0);
        c.ReplaceObject(3305, 9375, 6451, -1, 0);
        int time = 600;
        c.AddToCords(distance, 0, time);
        EventManager.getInstance().registerEvent(new Event(time) {
            public void execute() {
                if (c.disconnected) {
                    stop();
                    return;
                }
                c.ReplaceObject(3305, 9376, 6452, 0, 0);
                c.ReplaceObject(3305, 9375, 6451, 0, 0);
                c.UsingAgility = false;
                stop();
            }
        });
    }

}